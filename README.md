# Ktorage
#### A non-RESTful Server for [EDM Storm](https://github.com/DevRyz3n/Edm-Storm). 

Ktorage provides content for [EDM Storm](https://github.com/DevRyz3n/Edm-Storm) by crawling posts from some music producers / music labels on Instagram.

## Before using it you should know


### [instagram-crawler](https://github.com/huaying/instagram-crawler)

To save time, I use existing [instagram-crawler](https://github.com/huaying/instagram-crawler) and integrated instagram-crawler into the Gradle of Ktorage, which makes it easier for me to launch instagram-crawler crawler. Therefore, managing and launching instagram-crawler crawlers becomes part of Ktorage.

I suggest you take a look at the use of instagram-crawler -> [https://github.com/huaying/instagram-crawler#crawler](https://github.com/huaying/instagram-crawler#crawler)


### Ktor
 - [Kotlin Programming Language](https://kotlinlang.org/docs/reference/)
 - [Ktor - Quick Start ](https://ktor.io/quickstart/index.html)

I recommend you refer to this -> [[kotlin-ktor-exposed-starter](https://github.com/raharrison/kotlin-ktor-exposed-starter)]. Because I have a lot of reference to this project during development.



## Usage notice

The "crawl.txt" file located in the root directory of Ktorage is the crawling target for managing Instagram-crawler. Here you can set (add / delete) the username of the crawler (one username per line).

After you have set the crawl target, execute crawlAll() in "build.gradle" file to launch instagram-crawler. In order to write the crawled data to the database, don't forget to run the Application's "main()" to start the server.

When using for the first time, run "crawlAll()" in "build.gradle" instead of main() in the "Application.kt".
