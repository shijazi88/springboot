# spring boot
In This Repository, I will try to write some examples about spring boot, please check Wiki tab for more details

## Examples
- Rest Services
- Cache
- JDBC Template

## Rest Services Example
-----------------------------------------------------------------
## concepts
We will explain the following annotations:
- @Controller vs @RestController
- Get, Post, Put, Delete examples
- @RequestBody, @ResponseBody, @ResponseEntity
- @PathVariable, @RequestParam
- JSON vs XML
- Manage the return JSON (remove fields, change the names of fields)

## Notes

- In this example I will create **GET**, **PUT**, **DELETE**, **POST** http requests, and I will use **@Controller**, **@ResponseBody**, **@PathVariable**, **@RequestBody**

- In methods getUserByUsingRequestParam and getUserBy, I used:
**produces = **{MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}**. This additional attribute control the fromat of the output (JSON/XML)
- Client can choose the format by sending header (**Accept**) in the request
**(curl -X GET -H "Accept: application/json"  "http://localhost:8080/api/users/1")**
- If the client does not choose the format, JSON will be default format, because it's the first element in producess attribute
