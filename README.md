# 🧪 Trello API Automation Framework
Trello REST API üzerinde uçtan uca CRUD işlemlerini otomatik olarak test etmek için geliştirilmiş bir **Java + Rest Assured** tabanlı API test otomasyon projesidir.

Bu framework; board, list ve card işlemlerini kapsar, temiz bir test mimarisi, logging, random data üreteci, JSON seri/deserilazition ve yapılandırılabilir config yönetimi içerir.


## 🛠 Teknolojiler
Bu proje aşağıdaki teknolojiler kullanılarak geliştirilmiştir:

- **Java 21**
- **Rest-Assured 5.5.2**
- **JUnit 5**
- **Log4j2**
- **Jackson Databind**
- **Maven**
- **Lombok**

---

## ✨ Özellikler

### ✔ Board / List / Card CRUD testleri
- Create Board
- Create List
- Create Cards
- Update Card
- Get Boards
- Delete Board
- Delete All Boards

### ✔ Random test data üretimi
`RandomUtils` ile:
- random board name
- random list name
- random card name
- updated card name

### ✔ Config Manager
`config.properties` üzerinden:
- base URL
- API key
- API token

### ✔ Loglama
- Log4j2 log kayıtları
- Konsol logları
- Test step logları

### ✔ JSON Dönüşümleri
Rest Assured + Jackson ile model sınıflarına otomatik dönüşüm.
---

## Kurulum
1. Java 21 yüklü olmalı
```bash
java -version

**Maven Yükle**
mvn --version

**Projeyi Klonla**
git clone https://github.com/neseilhan/trello_api_automation.git

**config.properties Dosyasını Düzenle**
trello.base.url=https://api.trello.com/1
trello.api.key=YOUR_API_KEY
trello.api.token=YOUR_API_TOKEN

**Maven Bağımlılıklarını Yükle**
mvn clean install

**Testleri Çalıştır**
TrelloApiTest class → sağ tık → Run
ya da 
mvn test

**API Gereksinimleri**
Testlerin başarılı olması için Trello'dan alınmış API Key & Token gereklidir.
Testlerin başarılı olması için Trello'dan alınmış:

Doküman:
https://developer.atlassian.com/cloud/trello/rest/
