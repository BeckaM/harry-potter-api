# harry-potter-api

[![CircleCI](https://circleci.com/gh/yuhama/harry-potter-api.svg?style=svg)](https://circleci.com/gh/yuhama/harry-potter-api)

A CORS enabled example API, with Swagger docs, used in the Pink Programming [Pink Web Dev][pwd] initiative to showcase RESTful APIs. Contains one resource `/characters` and allows methods `GET`, `POST`, `DELETE`, and `PUT`. Additionally, each endpoint takes a `key` in query string to segment the API, preventing different users from modifying eachother's data.

Demo and Swagger docs live at <https://testapi.pinkwebdev.se/>.

## Usage

### Run the application locally

`lein ring server`

### Packaging and running as standalone jar

```
lein do clean, ring uberjar
java -jar target/server.jar
```

### Packaging as war

`lein ring uberwar`

## License

Copyright 2017 © Johanna Larsson <johanna.a.m.larsson@gmail.com>

Distributed under the Eclipse Public License, the same as Clojure.

[pwd]: http://pinkwebdev.se/
