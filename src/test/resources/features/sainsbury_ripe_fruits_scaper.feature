Feature: Scrape Sainsbury’s grocery site

  Background:
    Given there is no previous results

  Scenario: Successful scraping data from grocery site
    Given the grocery site is loaded
    When the grocery site is parsed
    Then there are 7 products with a total of 15.10
    And containing the following data
      | title                                                   | description | unit price | size |
      | Sainsbury's Apricot Ripe & Ready x5                     | Apricots    | 3.50       | 34kb |
      | Sainsbury's Avocado Ripe & Ready XL Loose 300g          | Avocados    | 1.50       | 35kb |
      | Sainsbury's Avocado, Ripe & Ready x2                    | Avocados    | 1.80       | 39kb |
      | Sainsbury's Avocados, Ripe & Ready x4                   | Avocados    | 3.20       | 35kb |
      | Sainsbury's Conference Pears, Ripe & Ready x4 (minimum) | Conference  | 1.50       | 35kb |
      | Sainsbury's Golden Kiwi x4                              | Gold Kiwi   | 1.80       | 35kb |
      | Sainsbury's Kiwi Fruit, Ripe & Ready x4                 | Kiwi        | 1.80       | 36kb |

  Scenario: Problem scraping data from grocery site
    Given the grocery site is loaded
    When the grocery site is not parsed
    Then there are 0 products with a total of 0.0
