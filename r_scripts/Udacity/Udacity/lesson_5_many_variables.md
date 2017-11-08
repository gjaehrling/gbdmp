Lesson 5 (many variables)
========================================================

Load data:

```r
library(ggplot2)
pf <- read.csv("~/programme/data_mining/R/Udacity/pseudo_facebook.tsv", sep = "\t", 
    header = TRUE, row.names = 1)
summary(pf)
```

```
##       age           dob_day        dob_year      dob_month    
##  Min.   : 13.0   Min.   : 1.0   Min.   :1900   Min.   : 1.00  
##  1st Qu.: 20.0   1st Qu.: 7.0   1st Qu.:1963   1st Qu.: 3.00  
##  Median : 28.0   Median :14.0   Median :1985   Median : 6.00  
##  Mean   : 37.3   Mean   :14.5   Mean   :1976   Mean   : 6.28  
##  3rd Qu.: 50.0   3rd Qu.:22.0   3rd Qu.:1993   3rd Qu.: 9.00  
##  Max.   :113.0   Max.   :31.0   Max.   :2000   Max.   :12.00  
##                                                               
##     gender          tenure      friend_count  friendships_initiated
##  female:40254   Min.   :   0   Min.   :   0   Min.   :   0         
##  male  :58574   1st Qu.: 226   1st Qu.:  31   1st Qu.:  17         
##  NA's  :  175   Median : 412   Median :  82   Median :  46         
##                 Mean   : 538   Mean   : 196   Mean   : 107         
##                 3rd Qu.: 675   3rd Qu.: 206   3rd Qu.: 117         
##                 Max.   :3139   Max.   :4923   Max.   :4144         
##                 NA's   :2                                          
##      likes       likes_received    mobile_likes   mobile_likes_received
##  Min.   :    0   Min.   :     0   Min.   :    0   Min.   :     0       
##  1st Qu.:    1   1st Qu.:     1   1st Qu.:    0   1st Qu.:     0       
##  Median :   11   Median :     8   Median :    4   Median :     4       
##  Mean   :  156   Mean   :   143   Mean   :  106   Mean   :    84       
##  3rd Qu.:   81   3rd Qu.:    59   3rd Qu.:   46   3rd Qu.:    33       
##  Max.   :25111   Max.   :261197   Max.   :25111   Max.   :138561       
##                                                                        
##    www_likes     www_likes_received
##  Min.   :    0   Min.   :     0    
##  1st Qu.:    0   1st Qu.:     0    
##  Median :    0   Median :     2    
##  Mean   :   50   Mean   :    59    
##  3rd Qu.:    7   3rd Qu.:    20    
##  Max.   :14865   Max.   :129953    
## 
```



Third Qualitative Variable

```r
ggplot(aes(x = gender, y = age), data = subset(pf, !is.na(gender))) + geom_boxplot() + 
    stat_summary(fun.y = mean, geom = "point", shape = 4)
```

![plot of chunk unnamed-chunk-2](figure/unnamed-chunk-21.png) 

```r

ggplot(aes(x = age, y = friend_count), data = subset(pf, !is.na(gender))) + 
    geom_line(aes(color = gender), stat = "summary", fun.y = median)
```

![plot of chunk unnamed-chunk-2](figure/unnamed-chunk-22.png) 


Second option for third qualitative variable:

```r
library("dplyr")
```

```
## 
## Attaching package: 'dplyr'
## 
## Die folgenden Objekte sind maskiert from 'package:stats':
## 
##     filter, lag
## 
## Die folgenden Objekte sind maskiert from 'package:base':
## 
##     intersect, setdiff, setequal, union
```

```r
pf.fc_gender <- filter(pf, !is.na(pf$gender))
group_age_gender <- group_by(pf.fc_gender, age, gender)

pf.fc_by_age_gender <- summarize(group_age_gender, friend_count_mean = mean(friend_count), 
    friend_count_median = median(friend_count), n = n())
head(pf.fc_by_age_gender)
```

```
## Source: local data frame [6 x 5]
## Groups: age
## 
##   age gender friend_count_mean friend_count_median    n
## 1  13 female             259.2                 148  193
## 2  13   male             102.1                  55  291
## 3  14 female             362.4                 224  847
## 4  14   male             164.1                  92 1078
## 5  15 female             538.7                 276 1139
## 6  15   male             200.7                 106 1478
```


Third option for qualitative variables:

```r
library("dplyr")

pf.fc_by_age_gender <- pf %.% filter(!is.na(gender)) %.% group_by(age, gender) %.% 
    summarize(mean_friend_count = mean(friend_count), median_friend_count = median(friend_count), 
        n = n()) %.% ungroup() %.% arrange(age)

head(pf.fc_by_age_gender)
```

```
## Source: local data frame [6 x 5]
## 
##   age gender mean_friend_count median_friend_count    n
## 1  13 female             259.2                 148  193
## 2  13   male             102.1                  55  291
## 3  14 female             362.4                 224  847
## 4  14   male             164.1                  92 1078
## 5  15 female             538.7                 276 1139
## 6  15   male             200.7                 106 1478
```


Plotting third qualitative variable:

```r
ggplot(aes(x = age, y = median_friend_count), data = pf.fc_by_age_gender) + 
    geom_line(aes(color = gender))
```

![plot of chunk unnamed-chunk-5](figure/unnamed-chunk-5.png) 


