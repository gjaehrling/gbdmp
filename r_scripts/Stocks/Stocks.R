# load libraries: 
library(tseries)
library(forecast)

# load the data: 
stocks <- read.csv("sp500.csv", header = TRUE, sep = " ")
summary(stocks)
head(stocks, n=10)
str(stocks)

# change Indes from Factor to Date:
stocks$Index <- as.Date(stocks$Index, format="%Y-%m-%d")

# first plot:
plot(stocks$Index, stocks$AdjClose, type="l")

# create time series
#stocks.ts <- ts(t(stocks$AdjClose))
#str(stocks.ts)

# calculate forecast using ARIMA model (auto regressive integrated moving average)
auto.arima(stocks$AdjClose)
fit <- arima(stocks$AdjClose, seasonal=list(order=c(0,1,0), period=200))
fc <- forecast(fit)

# plot results: 
plot(fc)
