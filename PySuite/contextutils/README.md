# contextutils

``contextutils`` is a Python module to manage context variables.

## Examples
```python
from contextutils import ContextStack

c = ContextStack()

c.foo = "foo"
with c:
    assert c.foo == "foo"

    c.foo = "bar"

    assert c.foo == "bar"

assert c.foo == "foo"
```

