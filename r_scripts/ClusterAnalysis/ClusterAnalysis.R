# load package for Cluster Analysis
library(mclust)
library(RMySQL)

# connect to database: 
con<-dbConnect(dbDriver("MySQL"), user = "root", password = "darmstadt", dbname = "bestellungen")
SalesOrders<-dbGetQuery(con, paste("select kdnr, 
                                   (year(curdate())) - (year(geburtsdatum)) as 'alter',
                                   ledig
                                   from
                                   kunde, auftrag, bestellung, artikel, kategorie
                                   where
                                   kunde is not NULL and
                                   kdnr = kunde and
                                   auftnr = auftrag and
                                   artikel = artikelnr and 
                                   kategorie = kategorienr limit 100;"))
summary(SalesOrders)
str(SalesOrders)
names(SalesOrders)

# test for customer clusters
cluster <- Mclust(Shop_Cluster[,2:5])
plot(cluster, Shop_Cluster[,2:5], what=c("classification", "uncertainty"))


cluster<-kmeans(Shop_Cluster, 5)
library(cluster) 
par(mfrow=c(1,1))
clusplot(Shop_Cluster, cluster$cluster, color=TRUE, shade=TRUE, labels=2, lines=0)
library(fpc)
plotcluster(Shop_Cluster, cluster$cluster)