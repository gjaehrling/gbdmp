#Install the R package arules
# install.packages("arules");

#load the arules package
library("arules");

# connect to MySQL database
# con<-dbConnect(dbDriver("MySQL"), user="root", password="darmstadt", dbname="bestellungen")
# list the tables in the database
# dbListTables(con)

# get results from query:
# concatenating auftrag and position
# res<-dbSendQuery(con, "select concat(auftrag, position) as \"auftrag\", artikel, artikelbezeichnung from bestellung, artikel where artikel=artikelnr order by auftrag asc;")
# res<-dbSendQuery(con, "select auftrag, artikel, artikelbezeichnung from bestellung, artikel where artikel=artikelnr order by auftrag asc;")
# res<-dbSendQuery(con, "select kunde, artikel, artikelbezeichnung from auftrag, bestellung, artikel where auftnr=auftrag and artikel=artikelnr and kunde IS NOT NULL order by auftrag asc;")
# res<-dbSendQuery(con, "select kunde, artikel from auftrag, bestellung, artikel where auftnr=auftrag and artikel=artikelnr and kunde IS NOT NULL order by auftrag asc;")

# SalesData<- fetch(res, n = -1)

# show summary:
# summary(SalesData)

# show structure
# str(SalesData)
#SalesData$auftrag<-as.factor(SalesData$auftrag)
# SalesData$kunde<-as.factor(SalesData$kunde)
# SalesData$artikel<-as.factor(SalesData$artikel)
# SalesData$artikelbezeichnung<-as.factor(SalesData$artikelbezeichnung)
# str(SalesData)
# View(SalesData)

MarketBasket<-read.transactions("Orders2.csv", rm.duplicates=TRUE, format="single", sep=";", cols=c(1,2))
inspect(MarketBasket)
summary(MarketBasket)

# Market Basket
basket_rules <- apriori(MarketBasket,parameter = list(sup = 1.0, conf = 1.0,target="rules"));

# Check the generated rules using inspect
inspect(basket_rules)

# itemFrequencyPlot(MarketBasket)