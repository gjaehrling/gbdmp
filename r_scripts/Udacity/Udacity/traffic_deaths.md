Deaths by traffic
========================================================

Load packages:

```r
library(ggplot2, suppressPackageStartupMessages)
```


Load data:

```r
getwd()
```

```
## [1] "/Users/gerd/programme/data_mining/R/Udacity/Udacity"
```

```r
traffic <- read.csv("~/programme/data_mining/R/Udacity/traffic_deaths.csv", 
    sep = ";", header = TRUE, row.names = 1)
```


Show summary:

```r
summary(traffic$X2007)
```

```
##    Min. 1st Qu.  Median    Mean 3rd Qu.    Max.    NA's 
##       1     298    1100    6910    5240  221000      47
```


Calculate totals 

```r
totals <- colSums(traffic[, -1])
```


first plot:

```r
qplot(Road.traffic.total.deaths, X2007, data = traffic, na.rm = TRUE)
```

```
## Error: Objekt 'Road.traffic.total.deaths' nicht gefunden
```


first plot switch axis:

```r
qplot(rowSums(traffic[, -1], na.rm = TRUE), Road.traffic.total.deaths, data = traffic, 
    na.rm = TRUE)
```

```
## Error: Objekt 'Road.traffic.total.deaths' nicht gefunden
```


top ten from traffic data frame:

```r
means <- apply(traffic, 1, mean, na.rm = T)
sorted.means <- sort(means, decreasing = T)
topten <- traffic[attr(sorted.means, "names")[1:10]]
```

```
## Error: undefined columns selected
```


plot the top10:

```r
qplot(row.names, 2007, data = topten, na.rm = TRUE)
```

```
## Error: Objekt 'topten' nicht gefunden
```



