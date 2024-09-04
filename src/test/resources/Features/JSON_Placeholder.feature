Feature: CRUD opertions on Rest Api Json Placeholder application


Scenario: GET all data 
   Given I have a base URI of "https://jsonplaceholder.typicode.com"
   When I send a GET request to "/posts"
   Then the response status code should be 200
   

  Scenario: GET one post
   Given I have a base URI of "https://jsonplaceholder.typicode.com/posts"
   When I send a GET request to "2"
   Then the response status code should be 200   
   

   Scenario Outline: Create post
     Given I have a base URI of "https://jsonplaceholder.typicode.com"
       When I send a POST request to "/posts/" with body as "<RequestBody>"
        Then the response time should be 5000

   Examples:

   |RequestBody                                                      |

   |{\\"title\\": \\"Book for Testing\\", \\"body\\": \\"creating API testing by posting\\", \\"userId\\": 1 }|


   Scenario Outline: Update post
       Given I have a base URI of "https://jsonplaceholder.typicode.com"
       When I send a PUT request to "/posts/2/" with body as "<RequestBody>"
       Then the response status code should be 200   

    Examples:

    |RequestBody                                                                                                                        |

    |{\\"id\\": 1,\\"title\\": \\"API textBook\\",\\"body\\": \\"Updated data in TextBook\\",\\"userId\\": 1}|

   
   
    Scenario: Delete post
    Given I have a base URI of "https://jsonplaceholder.typicode.com"
    When I send a DELETE request to "/posts/3"
    Then the response status code should be 200 