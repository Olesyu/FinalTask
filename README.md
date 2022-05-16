# testAutom
To run this project you need to have installed:
* java 8
* allure-2.17.3
* apache-maven-3.8.5
------------------------------------------------------------

To start testing with default params you need simply run
in the root of a cloned testAutom :
```shell
mvn clean test
```
This command will start test with this default params:
* `suite` - testng
* `browser` - chrome
* `threads` - 1
* `resolution` - full

## Available params

### -Dsuite
Here you can set testng.xml file with tests configurations. Important! Use only file name without extension!

### -Dbrowser
Here you can set browser to run test, available browsers:
* `chrome`
* `firefox`
* `safari`

chrome is default one!

### -Dthreads
Here you can set number of threads, that will be using all available cores.
Default value is `1`

### -Dresolution
Set resolution to start test with. Full screen is used by default.
###### IMPORTANT
    You need to set resolution in format `{width}x{height}`
    For example: `1280x960`

### EXAMPLE usage
```shell
mvn clean test -Dresolution=1280x960 -Dbrowser=chrome -Dthreads=3
```

### Also, there is possibility to start "allure" to check results
Simply run:
```shell
mvn allure::serve
```

