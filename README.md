# Run app:

java -jar smart-analyzer.jar <input_origin_file_path> <input_other_sample_file_path> <target_element_id>

# Results:

- sample-1-evil-gemini.html:
```
html[0]  > body[1]  > div[0]  > div[1]  > div[2]  > div[0]  > div[0]  > div[1]  > a[1]

This element was chosen because:
Attribute 'onclick' with value: 'javascript:window.okDone(); return false;' matched
Attribute 'title' with value: 'Make-Button' matched
Attribute 'class' with value: 'btn btn-success' matched
Element's text matched
```
- sample-2-container-and-clone.html:
```
html[0]  > body[1]  > div[0]  > div[1]  > div[2]  > div[0]  > div[0]  > div[1]  > div[0]  > a[0]

This element was chosen because:
Attribute 'rel' with value: 'next' matched
Attribute 'href' with value: '#ok' matched
Attribute 'title' with value: 'Make-Button' matched
Element's text matched
```
- sample-3-the-escape.html:
```
html[0]  > body[1]  > div[0]  > div[1]  > div[2]  > div[0]  > div[0]  > div[2]  > a[0]

This element was chosen because:
Attribute 'onclick' with value: 'javascript:window.okDone(); return false;' matched
Attribute 'rel' with value: 'next' matched
Attribute 'href' with value: '#ok' matched
Attribute 'class' with value: 'btn btn-success' matched
```

- sample-4-the-mash.html:
```

html[0]  > body[1]  > div[0]  > div[1]  > div[2]  > div[0]  > div[0]  > div[2]  > a[0]

This element was chosen because:
Attribute 'rel' with value: 'next' matched
Attribute 'href' with value: '#ok' matched
Attribute 'title' with value: 'Make-Button' matched
Attribute 'class' with value: 'btn btn-success' matched
```