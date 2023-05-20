# Final Project: Vietnam's History 🇻🇳
> The original project description can be found [here](https://docs.google.com/document/d/1dI-0LJEQR6v6rokB1idBbUu1vkbUhVn2/edit).

## 📂 How to navigate this repository?
Source code is located in the `webscraper/src` folder. The `out` folder contains the data collected from the web scraper.
## 🦤 Requirements
The entities need to be collected includes:
* Dynasties
* Historical figures (kings, queens, commanders, philosophers,...)
* Monuments - travel destinations (temples, churches,...) and historical places
* Cultural festivals & celebrations
* Historical events

Each entity will have identification, properties and needs to be linked together. 
For example, an event `Den Hung Festival` should be linked to the destination `Den Hung` and the historical figure `Hung King`.

## ⚙️ What do we need to build?
1. To collect data, a web scraper should be implemented. This scraper should be able to: (1) Collect data; (2) Save data to file; (3) Clean up data.
2. The user should be able to search and retrieve data, so a proper interface (either GUI or command-line prompt) should be built.

## 🔖 References
> This document contains the list of websites to get the data from. 
### 🌐 Wikipedia
Wikipedia exists in multiple languages, however we only need data mostly from Vietnamese and English ones. Wiki pages have lots of links to other pages, both inside Wikipedia and outside of it. We only need to find the biggest, most general pages that lead to other sub-topics.
The following links are organized by a hierarchical structure, from the most general to the more detailed ones.

* [Cổng thông tin: Việt Nam](https://vi.wikipedia.org/wiki/C%E1%BB%95ng_th%C3%B4ng_tin:Vi%E1%BB%87t_Nam)
* [Thể loại: Việt Nam](https://vi.wikipedia.org/wiki/Th%E1%BB%83_lo%E1%BA%A1i:Vi%E1%BB%87t_Nam)
* [Văn hóa Việt Nam](https://vi.wikipedia.org/wiki/V%C4%83n_h%C3%B3a_Vi%E1%BB%87t_Nam)
* [Lịch sử Việt Nam](https://vi.wikipedia.org/wiki/L%E1%BB%8Bch_s%E1%BB%AD_Vi%E1%BB%87t_Nam)
* [Lễ hội Việt Nam](https://vi.wikipedia.org/wiki/L%E1%BB%85_h%E1%BB%99i_Vi%E1%BB%87t_Nam)
* [Sách: Lịch sử Việt Nam](https://vi.wikipedia.org/wiki/S%C3%A1ch:L%E1%BB%8Bch_s%E1%BB%AD_Vi%E1%BB%87t_Nam)
* [Danh sách di sản thế giới tại Việt Nam](https://vi.wikipedia.org/wiki/Danh_s%C3%A1ch_di_s%E1%BA%A3n_th%E1%BA%BF_gi%E1%BB%9Bi_t%E1%BA%A1i_Vi%E1%BB%87t_Nam)
* [Du lịch Việt Nam](https://vi.wikipedia.org/wiki/Du_l%E1%BB%8Bch_Vi%E1%BB%87t_Nam)

* [Vietnam](https://en.wikipedia.org/wiki/Vietnam)
* [History of Vietnam](https://en.wikipedia.org/wiki/History_of_Vietnam)
* [List of World Heritage Sites in Vietnam](https://en.wikipedia.org/wiki/List_of_World_Heritage_Sites_in_Vietnam)

### 🗨️ Others
* [Bảo tàng lịch sử quốc gia](https://baotanglichsu.vn/vi)
* [**Người kể sử**](nguoikesu.com) 📌
* [Vietnam War 50th Anniversary](https://www.vietnamwar50th.com/)
* [Vietnam Online](https://www.vietnamonline.com/)
* [Vietnam Travel](https://vietnam.travel/)
* [The Vietnam Archive | TTU](https://www.vietnam.ttu.edu/virtualarchive/)
