# libraries
library(mclust)

# load data
film_analysis <- read.csv("~/Dropbox/user_gerd/programme/data_mining/R/FilmAnalysis/film_analysis.csv")

head(film_analysis)
summary(film_analysis)

# create time-series object
Fa.ts<-aggregate(x=film_analysis$rental_time, by=list(film=film_analysis$film_id), FUN=ts)
names(Fa.ts)<-c("Film", "TimeSeries")
str(Fa.ts[1:3,])

# mean value of time series
Fa.mean<-sapply(Fa.ts[, "TimeSeries"], FUN=mean)
Fa<-cbind(Fa.ts, Mean=Fa.mean)
Fa<-cbind(Fa, StdDev=sapply(Fa.ts[, "TimeSeries"], FUN=sd))

Fa.nr<-aggregate(x=film_analysis$rental_time, by=list(Customer=film_analysis$customer_id), FUN=length)
names(Fa.nr)<-c("Customer", "Nr.Orders")
Fa<-merge(Fa,Fa.nr)
Fa<-Fa[,c(1,3,4,6,5,2)]
str(Fa[1:3,])

# find film clusters
cl.m<-Mclust(Fa[1:6000,2:3])
cl.m$parameters$mean

plot(cl.m, Fa[1:6000,2:3], what="classification", dimens=c(1,2), cex=0.1)
