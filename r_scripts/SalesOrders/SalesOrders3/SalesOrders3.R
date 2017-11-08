library(RMySQL) 
con<-dbConnect(dbDriver("MySQL"), user = "root", password = "darmstadt", dbname = "bestellungen")
SalesOrders<-dbGetQuery(con, paste("select Kunde as BuyerRef, Mahnung,
                          LieferDat as DeliveryDate,
                          ZahlDat as DueDate,
                          ZahlEingang as DatePayed,
                          datediff(ZahlDat, LieferDat) as PaymentTerm,
                          (datediff(ZahlEingang, ZahlDat)) - (datediff(LieferDat, ZahlDat)) as PaymentMade,
                          datediff(ZahlEingang, ZahlDat) as PaymentDeviation,
                          Auftrag,
                          sum(Anzahl * Einzelpreis) as TotalValue
                          from
                          bestellung,
                          auftrag,
                          artikel
                          where
                          Auftrag = AuftNr and Artikel = ArtikelNr
                          and Kunde is not NULL
                          and ZahlEingang is not NULL
                          and Mahnung = '0'
                          and Auftrag!='4615'
                          group by Auftrag;
                          "))
SalesOrders$DeliveryDate<-as.Date(SalesOrders$DeliveryDate, format="%Y-%m-%d")
SalesOrders$DueDate<-as.Date(SalesOrders$DueDate, format="%Y-%m-%d")
SalesOrders$DatePayed<-as.Date(SalesOrders$DatePayed, format="%Y-%m-%d")

summary(SalesOrders)
str(SalesOrders)
names(SalesOrders)
par(mfrow=c(2,2))
hist(SalesOrders$DueDate, "weeks")
hist(SalesOrders$PaymentDeviation)
lines(density(SalesOrders$PaymentDeviation))
rug(jitter(SalesOrders$PaymentDeviation))
plot(SalesOrders$BuyerRef, SalesOrders$PaymentMade, cex=0.3)
smoothScatter(SalesOrders$TotalValue, SalesOrders$PaymentMade)
dbDisconnect(con)