# Example Implementation of a Hexagonal Architecture

[![CI](https://github.com/thombergs/buckpal/actions/workflows/ci.yml/badge.svg)](https://github.com/thombergs/buckpal/actions/workflows/ci.yml)

[![Get Your Hands Dirty On Clean Architecture](https://reflectoring.io/assets/img/get-your-hands-dirty-260x336.png)](https://reflectoring.io/book)

This is the companion code to my eBook [Get Your Hands Dirty on Clean Architecture](https://leanpub.com/get-your-hands-dirty-on-clean-architecture).

It implements a domain-centric "Hexagonal" approach of a common web application with Java and Spring Boot. 

## Companion Articles

* [Hexagonal Architecture with Java and Spring](https://reflectoring.io/spring-hexagonal/)
* [Building a Multi-Module Spring Boot Application with Gradle](https://reflectoring.io/spring-boot-gradle-multi-module/)

## Prerequisites

* JDK 11
* this project uses Lombok, so enable annotation processing in your IDE


## 과제
* 주식
  * 매수
* 요구사항 
  * 매수 수량은 최소 1, 최대 10 까지 설정 할 수 있다. 
  * 매수 가격은 0 이상 이다.
  * 매수 수량*가격이 10,000,000 원을 넘게 구매할 수 없다.
  * 매수 가능한 잔액이 있어야 매수 가능하다.
  * ...
