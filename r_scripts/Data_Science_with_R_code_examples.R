getwd()
# get available list of data sources in the R package: 
data()

# load a dataset: 
data("iris")
# to get the the type of the dataset use the class function: 
class(iris)
# example to save data as a file in the current workspace:
save(iris, file="myData.RData")
