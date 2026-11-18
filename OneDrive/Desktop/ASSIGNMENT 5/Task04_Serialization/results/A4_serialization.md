# A4 - Object Serialization

The serialization example writes a small hostel/room/resident object graph.
Durable values such as names and registration numbers can be serialized, while
live connections and credentials should remain transient. Those transient
fields are not restored from the byte stream and must be re-created only when
needed.

An explicit `serialVersionUID` makes class-version compatibility easier to
control. Adding fields requires a deliberate default or migration policy.
Native Java serialization is Java-specific and must not be used on untrusted
input. CSV or JSON is more suitable for interoperable data exchange.
