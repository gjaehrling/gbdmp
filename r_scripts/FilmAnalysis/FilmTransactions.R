#load the arules package
library("arules");

FilmTransactions<-read.transactions("FilmTransactions.csv", format="single", rm.duplicates=TRUE, sep=",", cols=c(1,10))
inspect(FilmTransactions[1:5,])
image(FilmTransactions)

basket_rules <- apriori(FilmTransactions,parameter = list(sup = 0.8, conf = 0.8,target="rules"));

# Check the generated rules using inspect
inspect(basket_rules)

summary(basket_rules)
