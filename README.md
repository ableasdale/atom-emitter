
get the latest jaxb (https://github.com/eclipse-ee4j/jaxb-ri/releases/tag/3.0.0-RI - recent Nov 25, 2020)


make the generated directory

```
./bin/xjc.sh -d generated https://raw.githubusercontent.com/metaleap/go-xsd-pkg/master/kbcafe.com/rss/atom.xsd.xml                                Sat 19 Dec 09:03:08 2020
Java major version: 14
parsing a schema...
compiling a schema...
org/w3/_2005/atom/CategoryType.java
org/w3/_2005/atom/ContentType.java
org/w3/_2005/atom/DateTimeType.java
org/w3/_2005/atom/EntryType.java
org/w3/_2005/atom/FeedType.java
org/w3/_2005/atom/GeneratorType.java
org/w3/_2005/atom/IconType.java
org/w3/_2005/atom/IdType.java
org/w3/_2005/atom/LinkType.java
org/w3/_2005/atom/LogoType.java
org/w3/_2005/atom/ObjectFactory.java
org/w3/_2005/atom/PersonType.java
org/w3/_2005/atom/SourceType.java
org/w3/_2005/atom/TextType.java
org/w3/_2005/atom/UriType.java
org/w3/_2005/atom/package-info.java
```