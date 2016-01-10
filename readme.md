# useless-jsptags *by NilsCoding*

While the JSP Tags provided by this project might not be very useful, it is intended to be a simple example of how to compose a Maven project for a JSP Taglib which can easily be included in a web project.

# tl;dr the key ingredients

## embedding the TLD file

The TLD file is located at `src/main/resources/META-INF/tags`.

The base path `src/main/resources` includes the file into the JAR.

The webserver will look inside the JAR in the `META-INF/tags` folder to find and include the project TLD.

## dependencies

The JSP API is needed as a compile dependency.

```
<dependency>
    <groupId>javax.servlet.jsp</groupId>
    <artifactId>jsp-api</artifactId>
    <version>2.0</version>
    <scope>provided</scope>
</dependency>
```

Using the scope `provided` causes the JSP API jar file not to be included in a referring web project. This prevents problems with the JSP API in the webserver.

# further reading

I've written a more detailled article (in german) which can be found [here](http://www.nilscoding.com/12_16/jsp_taglib_maven_jar.html).

# Copyright / License

*useless-jsptags* is licensed under the MIT License

## The MIT License (MIT)

Copyright (c) 2015 NilsCoding

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE. 


