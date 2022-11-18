###############################################################################
#    compile  only:   run.ps1 cc
#         run only:   run.ps1 r
# compile  and run:   run.ps1 c
###############################################################################
param($1,$2,$3,$4, $5, $6, $7, $8, $9)

$zh_classpath="classes;"
$src="src/CHC5223"
$main="CHC5223.CHC5223"


function run_java{
    param($1,$2,$3,$4, $5, $6, $7, $8, $9)
    java -cp $zh_classpath $main $1 $2 $3 $4 $5 $6 $7 $8 $9
}

# if ($PSBoundParameters.count -eq 0){
#     write-host "Please choose type compile(c) or run(r)"
#     exit 0
# }

if(($1 -eq "c") -or ($1 -eq "compile")){
    javac -d classes -cp $zh_classpath -encoding utf-8 $src/*.java
    if ($LASTEXITCODE -eq 0){
        run_java $2 $3 $4 $5 $6 $7 $8 $9
    }
}elseif(($1 -eq "cc")){  # only compile
    javac -d classes -cp $zh_classpath -encoding utf-8 $src/*.java
}elseif(($1 -eq "m")){  # only compile
    javac -d classes -cp $zh_classpath -encoding utf-8 $src/*.java
    $main=$2
    if ($LASTEXITCODE -eq 0){
        run_java $2 $3 $4 $5 $6 $7 $8 $9
    }
}else{
    run_java $2 $3 $4 $5 $6 $7 $8 $9
}

# javac -cp ".;../lib/*" *.java

# dir /s /B src\*.java > sources.txt


