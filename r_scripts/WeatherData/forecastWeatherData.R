# load packages and load them to the workspeace
install.packages("tseries", dependencies=TRUE)
install.packages("forecast", dependencies=TRUE)
library(tseries)
library(forecast)

# load the data and do a first check:
weather <- read.csv("M4_not_cleaned.csv", header=TRUE)
names(weather)
head(weather, n=10)
summary(weather)
str(weather)

# cleaning the rows with NA values
weather.clean <- na.omit(weather)
weather.clean <- weather[complete.cases(weather), ]
row.names(weather.clean) <- NULL
View(weather.clean)

# plot the athmosphere pressure: 
atm.prs <- weather.clean$AtmosphericPressure.mb
str(atm.prs)
plot(atm.prs)

# create time series: 
atm.prs <- ts(weather.clean$AtmosphericPressure.mb, frequency=7)
#atm.prs<- msts(weather.clean$AtmosphericPressure.mb, seasonal.periods=c(7,365.25))
plot.ts(atm.prs)

auto.arima(atm.prs)
fit=arima(atm.prs,seasonal=list(order=c(0,1,0),period=500))
plot(forecast(fit))
