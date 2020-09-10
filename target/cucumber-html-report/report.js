$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/features/SearchAProduct.feature");
formatter.feature({
  "name": "In order to test SearchBox element works fine in MAX Home page",
  "description": "         As a user",
  "keyword": "Feature",
  "tags": [
    {
      "name": "@Search"
    }
  ]
});
formatter.scenarioOutline({
  "name": "In order to validate if for every search three sections Departments,Products and See Matching All options are present in the list",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "User opens a \"browserChrome\"",
  "keyword": "Given "
});
formatter.step({
  "name": "User navigates to Max Home \"url\"",
  "keyword": "And "
});
formatter.step({
  "name": "User searches for product \"\u003cproductname\u003e\"",
  "keyword": "When "
});
formatter.step({
  "name": "User sees first section \"Departments\" list",
  "keyword": "Then "
});
formatter.step({
  "name": "User sees second section \"Products\" list",
  "keyword": "And "
});
formatter.step({
  "name": "User sees \"matching products\" link",
  "keyword": "And "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "productname"
      ]
    },
    {
      "cells": [
        "jacket in kids"
      ]
    }
  ]
});
formatter.scenario({
  "name": "In order to validate if for every search three sections Departments,Products and See Matching All options are present in the list",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@Search"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "User opens a \"browserChrome\"",
  "keyword": "Given "
});
formatter.match({
  "location": "MaxAddToBasketFunctionality.user_opens_a(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User navigates to Max Home \"url\"",
  "keyword": "And "
});
formatter.match({
  "location": "MaxAddToBasketFunctionality.user_navigates_to_Max_Home(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User searches for product \"jacket in kids\"",
  "keyword": "When "
});
formatter.match({
  "location": "MaxAddToBasketFunctionality.user_searches_for_product(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User sees first section \"Departments\" list",
  "keyword": "Then "
});
formatter.match({
  "location": "MaxAddToBasketFunctionality.user_sees_first_section_list(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User sees second section \"Products\" list",
  "keyword": "And "
});
formatter.match({
  "location": "MaxAddToBasketFunctionality.user_sees_second_section_list(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User sees \"matching products\" link",
  "keyword": "And "
});
formatter.match({
  "location": "MaxAddToBasketFunctionality.user_sees_link(String)"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
formatter.scenarioOutline({
  "name": "In order to validate that user can add product into basket from See Matching All Products link",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "User opens a \"browserChrome\"",
  "keyword": "Given "
});
formatter.step({
  "name": "User navigates to Max Home \"url\"",
  "keyword": "And "
});
formatter.step({
  "name": "User searches for product \"\u003cproductname\u003e\"",
  "keyword": "When "
});
formatter.step({
  "name": "User clicks on \"matching products\" link",
  "keyword": "And "
});
formatter.step({
  "name": "User is navigated to the \"\u003csearchedPageTitle\u003e\" page",
  "keyword": "Then "
});
formatter.step({
  "name": "User checks for the available filters",
  "keyword": "And ",
  "rows": [
    {
      "cells": [
        "Price"
      ]
    },
    {
      "cells": [
        "Size"
      ]
    },
    {
      "cells": [
        "Color Family"
      ]
    },
    {
      "cells": [
        "Design"
      ]
    },
    {
      "cells": [
        "Type"
      ]
    },
    {
      "cells": [
        "Style"
      ]
    },
    {
      "cells": [
        "Sleeve Length"
      ]
    },
    {
      "cells": [
        "Browser"
      ]
    },
    {
      "cells": [
        "Promotions"
      ]
    }
  ]
});
formatter.step({
  "name": "User and clicks on the \"ordinal\" product",
  "keyword": "And "
});
formatter.step({
  "name": "User adds the product to basket",
  "keyword": "Then "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "productname",
        "searchedPageTitle"
      ]
    },
    {
      "cells": [
        "jacket in kids",
        "jacket in kids"
      ]
    }
  ]
});
formatter.scenario({
  "name": "In order to validate that user can add product into basket from See Matching All Products link",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@Search"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "User opens a \"browserChrome\"",
  "keyword": "Given "
});
formatter.match({
  "location": "MaxAddToBasketFunctionality.user_opens_a(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User navigates to Max Home \"url\"",
  "keyword": "And "
});
formatter.match({
  "location": "MaxAddToBasketFunctionality.user_navigates_to_Max_Home(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User searches for product \"jacket in kids\"",
  "keyword": "When "
});
formatter.match({
  "location": "MaxAddToBasketFunctionality.user_searches_for_product(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User clicks on \"matching products\" link",
  "keyword": "And "
});
formatter.match({
  "location": "MaxAddToBasketFunctionality.user_clicks_on_link(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User is navigated to the \"jacket in kids\" page",
  "keyword": "Then "
});
formatter.match({
  "location": "MaxAddToBasketFunctionality.user_is_navigated_to_the_page(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User checks for the available filters",
  "rows": [
    {
      "cells": [
        "Price"
      ]
    },
    {
      "cells": [
        "Size"
      ]
    },
    {
      "cells": [
        "Color Family"
      ]
    },
    {
      "cells": [
        "Design"
      ]
    },
    {
      "cells": [
        "Type"
      ]
    },
    {
      "cells": [
        "Style"
      ]
    },
    {
      "cells": [
        "Sleeve Length"
      ]
    },
    {
      "cells": [
        "Browser"
      ]
    },
    {
      "cells": [
        "Promotions"
      ]
    }
  ],
  "keyword": "And "
});
formatter.match({
  "location": "MaxAddToBasketFunctionality.user_checks_for_the_available_filters(DataTable)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User and clicks on the \"ordinal\" product",
  "keyword": "And "
});
formatter.match({
  "location": "MaxAddToBasketFunctionality.user_and_clicks_on_the_product(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User adds the product to basket",
  "keyword": "Then "
});
formatter.match({
  "location": "MaxAddToBasketFunctionality.user_adds_the_product_to_basket()"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
});