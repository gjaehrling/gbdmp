library(car)
summary(mtcars)
head(mtcars)
fit<-lm(mpg~disp+hp+wt+drat, data=mtcars)
outlierTest(fit)
qqPlot(fit, main="QQ Plot")
leveragePlots(fit)
av.Plots(fit)