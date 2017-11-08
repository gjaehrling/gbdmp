#Udacity Lesson 1

setwd('/Users/gerd/programme/data_mining/R/Udacity')
statesInfo <- read.csv('stateData.csv')
subset(statesInfo, state.region == 1)

stateInfoBracket <- statesInfo[statesInfo$state.region == 1, ]
head(stateInfoBracket)
dim(stateInfoBracket)

mtcars
summary(mtcars)
subset(mtcars, mpg >= 30 | hp < 60)

data <- read.csv('reddit.csv')
str(data)
table(data$employment.status)
levels(data$age.range)
install.packages("ggplot2")
library(ggplot2)

qplot(data = data, x = factor(age.range))
qplot(data = data, x = income.range)

# re-order the levels in the order of appearance in the data.frame
ordered_age <- data$age.range
data$age.range <- factor(data$age.range, levels=c("Under 18", "18-24", "25-34", "35-44", "45-54", "55-64", "65 or Above"))
qplot(data = data, x = age.range)
qplot(data = data, x = factor(data$age.range, levels=c("Under 18", "18-24", "25-34", "35-44", "45-54", "55-64", "65 or Above"),ordered = TRUE))
