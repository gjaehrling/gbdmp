# load libraries:
library(tseries)
library(forecast)

# load the data: 
SalesOrders2 <- read.csv("SalesOrders2.csv", header = TRUE, sep = "\t")
SalesOrders2$DeliveryDate <- as.Date(SalesOrders2$DeliveryDate, format="%Y-%m-%d") 
names(SalesOrders2)
head(SalesOrders2, n=10)
summary(SalesOrders2)
str(SalesOrders2)

# plot some data:
PaymentDuration <- SalesOrders2$PaymentDuration
str(PaymentDuration)
#plot(PaymentDuration, type="o", col="blue")

dotchart(PaymentDuration,labels=row.names(SalesOrders2$PaymentDuration),cex=.6,
         main="Payments per day",
         xlab="Transaction value")

# create time series: 
Price.ts <- aggregate(x=SalesOrders2$TotalPrice, 
                      by=list(SalesOrders2$DeliveryDate), 
                      FUN="sum")
str(Price.ts[1:3, ])
ts <- ts(t(Price.ts$x)) 
hist(ts, breaks=100, col="grey", main="Distribution of order values")
plot(Price.ts$Group.1, Price.ts$x, type="o", col="black")
abline(h=mean(Price.ts$x), col="red")
# alternative plot:
plot(ts[1,],type="l",col="black") 
abline(h=mean(Price.ts$x), col="white")

# calculate forecast:
auto.arima(Price.ts$x)
fit <- arima(Price.ts$x, seasonal = list(order=c(0,1,0), period=80))
fc <- forecast(fit)
plot(fc, main="Forecast from ARIMA")
abline(h=mean(Price.ts$x), col="white")
# plot with limited x-axis:
plot(fc, main="Forecast from ARIMA model (details)", xlab="number of days", ylab="odered value",
     xlim=c(800, 1000))
abline(h=mean(Price.ts$x), col="red")

