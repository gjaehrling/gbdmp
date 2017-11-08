install.packages("sparklyr")
install.packages(c("nycflights13", "Lahman"))
install.packages("leaflet")

# ###################################################
library(sparklyr)
library(dplyr)
library(DBI)
library(datasets)
library(SparkR, lib.loc = c(file.path(Sys.getenv("SPARK_HOME"), "R", "lib")))

#spark_install(version = "2.0.0")
#devtools::install_github("rstudio/sparklyr")
Sys.getenv("SPARK_HOME")
Sys.getenv("SPARK_HOME_VERSION")
Sys.setenv(SPARK_HOME_VERSION="1.6.0")
sc <- spark_connect(master = "local", spark_home = Sys.getenv("SPARK_HOME"))
sc

dbGetQuery(sc, "use gbdmp")
check_tables <- dbGetQuery(sc, "show tables")
check_tables
dbGetQuery(sc, "describe business")
some_data = dbGetQuery(sc, "SELECT name, latitude, longitude from business limit 10")
some_data

# test with rworldmap:
# install.packages("rworldmap")
library(rworldmap)
newmap <- getMap(resolution = "low")
plot(newmap, xlim = c(-20, 59), ylim = c(35, 71), asp = 1)

# test with ggmap

