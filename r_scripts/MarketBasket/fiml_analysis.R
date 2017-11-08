#load the arules package
library("arules");

MarketBasket<-read.transactions("film_analysis_2.csv", rm.duplicates=TRUE, format="single", sep=";", cols=c(1,2))
inspect(MarketBasket)
summary(MarketBasket)

# Market Basket
basket_rules <- apriori(MarketBasket,parameter = list(sup = 0.1, conf = 0.1,target="rules"));

# Check the generated rules using inspect
inspect(basket_rules)

