# PySuite

---

The ```PySuite``` repository helps in the learning of the python language.



## Folder Structure Conventions

---

```
.PySuite/
├── contextutils                    # The basic python
├── README.md                       # Instructions and helpful links
├── robots.txt                      # tells which URLs the search engine crawlers can access on your site
├── <module>                        # The module service
└── /
```

## Python Projects Structures

| Folder           | Description                                   |
|:-----------------|:----------------------------------------------|
| /contextutils    | the contextutils library                      |


# Building Application

---

## Create Virtual Env
```shell
python3 -m pip install virtualenv
python3 -m venv venv
```

## Activate Virtual Environment

- Mac OS/Linux

```source``` is Linux/macOS command and doesn't work in Windows.

```shell
source venv/bin/activate

OR

. ./venv/bin/activate  
```

- Windows
```shell
venv\Scripts\activate
```

Output:
```
(venv) rslakra@YVXKPJV2CN Python % 
```

The parenthesized ```(venv)``` in front of the prompt indicates that you’ve successfully activated the virtual environment.

## Deactivate Virtual Env
```shell
deactivate
```

Output:
```
rslakra@YVXKPJV2CN % 
```

## Upgrade ```pip``` release

```shell
pip install --upgrade pip
```

## Install Packages/Dependencies

- Install at system level
```shell
brew install python-requests
```

- Install in specific Virtual Env
```shell
pip install requests
pip install beautifulsoup4
python -m pip install requests
```


- Install from Requirements .txt file
```shell
pip install -r requirements.txt
```

## Save Requirements (Dependencies)
```shell
pip freeze > requirements.txt
```


## Configuration Setup

Set local configuration file.

```shell
pip install python-dotenv
cp default.env .env
```

# Reference

- [Gunicorn Architecture](https://docs.gunicorn.org/en/latest/design.html)


# Author

---

- Rohtash Lakra

