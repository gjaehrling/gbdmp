# Vorbereitungen
library(car)
library(mclust)

# Einlesen der Daten aus der CSV-Datei
SalesOrdersHeise <- read.csv("SalesOrdersHeise.csv", sep=";", stringsAsFactor=FALSE)

# Anzeige der Struktur
str(SalesOrdersHeise)
# Konvertierung des Felds 'DeliveryDate' vom character nach date
SalesOrdersHeise$DeliveryDate<-as.Date(SalesOrdersHeise$DeliveryDate, format="%Y-%m-%d")

# Überprüfen der Struktur
str(SalesOrdersHeise)

# Teile das Ausgabefenster in eine 2,2 Felder Matrix
par(mfrow=c(2,2)) 
# Spaltennamen zuweisen
attach(SalesOrdersHeise)
# Spaltennamen ausgeben
names(SalesOrdersHeise)

# Histogramme
hist(BuyerRef)
hist(DeliveryDate, "weeks")
hist(TotalPrice)
hist(PaymentDuration, main="Zahlungsverhalten")
lines(density(PaymentDuration, na.rm=T))
rug(jitter(PaymentDuration))

par(mfrow=c(1,1))
qqPlot(PaymentDuration, main="Zahlungsverhalten QQ Plot")

# 2-dimensionale Korrelationsplots
par(mfrow=c(2,2)) 
plot(BuyerRef, PaymentDuration)
smoothScatter(BuyerRef, PaymentDuration)
smoothScatter(TotalPrice, PaymentDuration)
plot(TotalPrice, PaymentDuration, cex=0.3)

scatterplotMatrix(SalesOrdersHeise, diagonal="histogram", cex=0.5,smooth=FALSE, reg.line=FALSE)

# erzeuge die Zeitreihe für die Zahlungshistorie der Kunden
Pd.ts<-aggregate(x=SalesOrdersHeise$PaymentDuration, by=list(Customer=SalesOrdersHeise$BuyerRef), FUN=ts)
names(Pd.ts)<-c("Customer", "TimeSeries")
str(Pd.ts[1:3,])

# Durchschnittswert der Tage pro Kunde
Pd.mean<-sapply(Pd.ts[, "TimeSeries"], FUN=mean)

# Zusammenfügen des Vektors "Pd.mean" zu dem TimeSeries Data-Frames mit "cbind"
Pd<-cbind(Pd.ts, Mean=Pd.mean)

# Beispiel zur Kombination der beiden Befehle beim Hinzufügen des Wertes zur Standardabweichung
Pd<-cbind(Pd, StdDev=sapply(Pd.ts[, "TimeSeries"], FUN=sd))

# Hinzufügen der Anzahl Verkaufsaufträge zum TimeSeries Data-Frame
Pd.nr<-aggregate(x=SalesOrdersHeise$PaymentDuration, 
                by=list(Customer=SalesOrdersHeise$BuyerRef), FUN=length)
names(Pd.nr)<-c("Customer", "NrDel")
# Verbinden der beiden Data-frames
Pd<-merge(Pd, Pd.nr)

str(Pd)
# Umsortieren der Spalten
Pd <- Pd[, c(1,3,4,5,2)]
str(Pd[1:3,])
# Clusteranalyse mit Mclust
cl.m <- Mclust(Pd[,2:4])
# Ausgabe der Cluster-Zentren
cl.m$parameters$mean

# Ausgabe des Clusters
par(mfrow=c(1,1))
plot(cl.m, Pd[,2:4], dimens=c(1:2),what=c("classification"))
