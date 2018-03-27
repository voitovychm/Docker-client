tempfolder=tempjar

confiles="application.conf  routes config.properties DBconfig.properties logback.xml";
classfiles="Automatter Automattercontrollers Automatterviews com controllers Global.class";


## Clean tempfolder
rm -rf $tempfolder;
mkdir -p $tempfolder


classfolder=target/scala-2.10/classes/
conffolder=conf


cp -r public $tempfolder

for file in `echo $confiles`
do 
	cp -r $conffolder/$file  $tempfolder;
done


for file in `echo $classfiles`
do 
	cp -r $classfolder/$file  $tempfolder;
done


cd $tempfolder;

jar cvf automatter_2.10.jar  .
cp automatter_2.10.jar ../

cd ..;


## Comment to debug what went into the jar
rm -rf $tempfolder;