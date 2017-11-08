# market basket analysis: 
# from: http://www.salemmarafi.com/code/market-basket-analysis-with-r/comment-page-1/

library(arules)
library(arulesViz)
library(datasets)

# Load the data set
data(Groceries)

# Create an item frequency plot for the top 20 items
itemFrequencyPlot(Groceries,topN=20,type="absolute")

# Get the rules
rules <- apriori(Groceries, parameter = list(supp = 0.001, conf = 0.8))

# Show the top 5 rules, but only 2 digits
options(digits=2)
inspect(rules[1:5])

# sort the top rules first and show the ten most significant rules: 
rules <-sort(rules, by="confidence", decreasing=TRUE)
inspect(rules[1:10])

# by adding a “maxlen” parameter to your apriori function the length of the rule can be limited:
rules <- apriori(Groceries, parameter = list(supp = 0.001, conf = 0.8,maxlen=3))
inspect(rules[1:10])

# eliminate redundant rules: 
subset.matrix <- is.subset(rules, rules)
subset.matrix[lower.tri(subset.matrix, diag=T)] <- NA
redundant <- colSums(subset.matrix, na.rm=T) >= 1
rules.pruned <- rules[!redundant]
rules<-rules.pruned
inspect(rules[1:10])

# targetting items:
rules<-apriori(data=Groceries, parameter=list(supp=0.001,conf = 0.08), 
               appearance = list(default="lhs",rhs="whole milk"),
               control = list(verbose=F))
rules<-sort(rules, decreasing=TRUE,by="confidence")
inspect(rules[1:5])

# targetting 2: 
rules<-apriori(data=Groceries, parameter=list(supp=0.001,conf = 0.15,minlen=2), 
               appearance = list(default="rhs",lhs="whole milk"),
               control = list(verbose=F))
rules<-sort(rules, decreasing=TRUE,by="confidence")
inspect(rules[1:5])

# visualization:
library(arulesViz)
plot(rules,method="graph",interactive=TRUE,shading=NA)
