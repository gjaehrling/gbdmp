#Install the R package arules
# install.packages("arules");

#load the arules package
library("arules");

#movies <- read.csv("~/programme/data_mining/data/movies.csv", sep=";")
movies<-read.transactions(file="~/programme/data_mining/data/movies.csv", rm.duplicates= FALSE, format="single",sep=";",cols =c(1,2))

inspect(movies)

head(movies)

# movies$ID<-as.factor(movies$ID)
# str(movies)
summary(movies)
basket_rules <- apriori(movies, parameter = list(sup = 0.3, conf = 0.9,target="rules"));

# Check the generated rules using inspect
inspect(basket_rules)
movies <- read.table("~/programme/data_mining/data/movies.csv", sep=";", quote="\"")
