library(DMwR)
library(xts)
library(DBI)
library(RMySQL)

# Example time series
x1<-xts(rnorm(100), seq(as.POSIXct("2000-01-01"), len=100, by="day"))

x2<-xts(rnorm(100), seq(as.POSIXct("2000-01-01 13:00"), len=100,by="min"))

x3<-xts(rnorm(3), as.Date(c("2005-01-01", "2005-01-10", "2005-01-12")))

# connect to MySQL database:
# drv<-dbDriver("MySQL")
# ch<-dbConnect(drv, dbname="stock_market", "root", "darmstadt")
# allQuotes<-dbGetQuery(ch, "select * from gspc")
# GSPC<-xts(allQuotes[,-1], order.by=as.Date(allQuotes[,1]))

# Dax Values from CSV-file
GSPC <- read.csv("~/programme/data_mining/R/StockMarket/DAX.csv", sep=";")
GSPC$Date<-as.Date(GSPC$Date)
GSPC<-xts(allQuotes[,-1], order.by=as.Date(allQuotes[,1]))

head(GSPC)
dbDisconnect(ch)
dbUnloadDriver(drv)

T.ind<-function(quotes, tgt.margin=0.0025, n.days=10){
  v<-apply(HLC(quotes), 1, mean)
  r<-matrix(NA, ncol=n.days, nrow=NROW(quotes))
  for(x in 1:n.days) r[,x]<-Next(Delt(v,k=x),x)
  x<-apply(r, 1, function(x) sum(x[x>tgt.margin | x<tgt.margin]))
  if(is.xts(quotes))
    xts(x,time(quotes))
  else
    x
  }
candleChart(last(GSPC, "3 months"), theme="white", TA=NULL)
avgPrice<-function(p) apply(HLC(p), 1, mean)
addAvgPrice<-newTA(FUN=avgPrice, col=1, legend="AvgPrice")
addT.ind<-newTA(FUN=T.ind, col="red", legend="TgtRet")
addAvgPrice(on=1)
addT.ind()