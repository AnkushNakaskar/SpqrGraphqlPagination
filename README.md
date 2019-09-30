* This project of spring graphql with pagination example.
* First ,I would suggest read the difference between cursor and offset based pagination
    * https://dev.to/jackmarchant/offset-and-cursor-pagination-explained-b89
    * https://stackoverflow.com/questions/51795299/graphql-pagination-cursor-vs-offset
    
* You can see the Article service h
    * check method for articles ,It has below details
        * Check input params (first : it signify the limit the number of record , after : It is the cursor from which we want record)
        * I had fit this approached in spring data jpa.
* You need to have proper input GraphQL query to get records.
    * I have attached the input json example for it.
    ````
  query AllArticles {
    articles(first: 2, after: "1") {
      edges {
        node {
          id
          title
        }
        cursor
      }
      pageInfo {
        endCursor
        hasNextPage
      }
    }
  }

  Query Param :
  
  {
    "articleId": 1
  }
  ````        
* This project is exampled explain in articles only,You can implement in other API's as well. This project uses SPQR library for graphQL.
* You can refer the Link for More detail points read :
    * https://pragmaticcoders.com/blog/how-to-use-graphql-in-spring-boot/ 
    * https://dimitr.im/graphql-spring-boot
    * https://github.com/graphql-java/graphql-java-page/tree/master/content/documentation/v13
    * https://graphql.org/blog/subscriptions-in-graphql-and-relay/
    * https://github.com/leangen/graphql-spqr-spring-boot-starter
    * Must read for authorization : https://github.com/Blacktoviche/springboot-graphql-sqqr-jwt-demo
    
* You can refer the input json (postman from SpringGraphQL project).
* This project uses SPQR library just to automate the schema defination so that there should not be any miss match between resolver and query definations.
* Very soon this library will be integrated in spring boot starter.
* Classes to look into
    * Controller
    * Configuration
    
This is very awesome project i came across.Next project would be around the graphql subscription example.
