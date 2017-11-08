#Install the R package arules
install.packages("arules");

#load the arules package
library("arules");

WineryData<-read.transactions("/Users/gerd/Dropbox/user_gerd/programme/data_mining/R/WineryShopping/Wine.csv", format="single", rm.duplicates=TRUE, sep=";", cols=c(1,2))
inspect(WineryData)
#image(WineryData)

basket_rules <- apriori(WineryData,parameter = list(sup = 0.1, conf = 0.4,target="rules"));

# Check the generated rules using inspect
inspect(basket_rules)

summary(basket_rules)

itemFrequencyPlot(WineryData, sup=0.1)