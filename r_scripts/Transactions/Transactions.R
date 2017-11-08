library("arules");

MarketBasket<-read.transactions("SimpleTransactions.csv", rm.duplicates=TRUE, format="single", sep=",", cols=c(1,2))
inspect(MarketBasket)
summary(MarketBasket)

# Market Basket
basket_rules <- apriori(MarketBasket,parameter = list(sup = 0.5, conf = 0.9,target="rules"));

# Check the generated rules using inspect
inspect(basket_rules)
itemFrequencyPlot(MarketBasket)
image(MarketBasket)