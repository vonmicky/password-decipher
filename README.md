# Password Decipher
Decipher password from MD5, SHA-1, SHA-256, SHA-512 hashes.

Actually it tells which password was used to create a hash that is located at a certain line in a shadow file and save decoded password to a file in a directory named `output`

A directory named `files` contain :

* passwordFile.txt -  a file contain list of human-readable text "password" *i.e* 10,000 password for this file.
* shadowFile.txt - a file contain list of hash strings that is 260 hash in this file.

> You can replace `passwordFile.txt` and `shadowFile.txt` with your files for more hash and password but *they must have the same name as the ones before*.

## How to Run
You may compile source code and run it yourself.

#### *OR*

I save you some trouble and just follow this step.

Locate PasswordDecipher.jar on terminal and type:
```sh
java -jar PasswordDecipher.jar
```

> Note: Dont forget to install Java on your mashine.
