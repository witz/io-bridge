[![CircleCI](https://circleci.com/gh/witz/io-bridge.svg?style=shield)](https://circleci.com/gh/witz/io-bridge)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=net.witz.libs%3Aio-bridge-parent&metric=alert_status)](https://sonarcloud.io/dashboard?id=net.witz.libs%3Aio-bridge-parent)

# IO-Bridge

This library is intended to simplify data access through different protocols.
It defines a simple and common API to decouple Interfaces from their implementations by adopting the [Bridge Design Pattern](https://en.wikipedia.org/wiki/Bridge_pattern). 
Several implementations might be developed to cover multiple protocols.
Each protocol implementation must be contributed via a dedicated *connector* by extending the IOSource [SPI](https://en.wikipedia.org/wiki/Service_provider_interface).
Combining the Bridge Design Pattern and Java SPI allows building modular, customizable and extendable applications to cover different needs.
