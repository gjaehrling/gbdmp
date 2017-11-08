# preparation
library(car)
library(mclust)
# load data from csv file:
SalesOrders <- read.csv("/home/hadoop/SVNWorkplace/trunk/r_scripts/SalesOrders/SalesOrders.csv", 
                        sep=",",
                        stringsAsFactors=FALSE)
# change format for date fields:
SalesOrders$DeliveryDate <- as.Date(SalesOrders$DeliveryDate, format="%Y-%m-%d")
SalesOrders$DueDate <- as.Date(SalesOrders$DueDate, format="%Y-%m-%d")
SalesOrders$DatePayed <- as.Date(SalesOrders$DatePayed, format="%Y-%m-%d")

# view structure:
str(SalesOrders)
# show summary
summary(SalesOrders)
names(SalesOrders)

# display data:
par(mfrow=c(2,2))
hist(SalesOrders$BuyerRef)
hist(SalesOrders$DeliveryDate, "weeks")
hist(SalesOrders$TotalValue)
hist(SalesOrders$PaymentMade, main="Payment History")
lines(density(SalesOrders$PaymentMade, na.rm=T))
rug(jitter(SalesOrders$PaymentMade))

# QQ Plot of payment history
par(mfrow=c(1,1))
qqPlot(SalesOrders$PaymentMade)
abline(h=mean(SalesOrders$PaymentMade, na.rm=T), lty=2)

# create the time series for the payment history of the customers
Ph.ts<-aggregate(x=SalesOrders$PaymentMade, by=list(Customer=SalesOrders$BuyerRef), FUN=ts)
names(Ph.ts)<-c("Customer", "TimeSeries")
str(Ph.ts[1:3,])

# Average days per customer
Ph.mean<-sapply(Ph.ts[, "TimeSeries"], FUN=mean)

# bind the vector "Ph.mean" with the TimeSeries Data-Frames mit "cbind"
Ph<-cbind(Ph.ts, Mean=Ph.mean)

# example for the combination of the two commands for the standard-deviation values
Ph<-cbind(Ph, StdDev=sapply(Ph.ts[, "TimeSeries"], FUN=sd))

# add the number of sales orders to the TimeSeries Data-Frame
Ph.nr<-aggregate(x=SalesOrders$PaymentMade, 
                 by=list(Customer=SalesOrders$BuyerRef), FUN=length)
names(Ph.nr)<-c("Customer", "NrDel")
Ph<-merge(Ph, Ph.nr)

str(Ph[1:5,])

Ph <- Ph[, c(1,3,4,5,2)]
str(Ph[1:3,])

# cluster analysis
cl.m<-Mclust(Ph[,2:4])
# display the cluster centers
cl.m$parameters$mean

# plot the cluster
par(mfrow=c(1,1))
plot(cl.m, Pd[,2:4], dimens=c(1:2),what=c("classification"))