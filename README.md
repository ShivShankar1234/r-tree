# r-tree

An R-tree is a commonly used geospatial index.

This was fun to make, has an elegant and concise algorithm, is thread-safe, fast, and reasonably memory efficient (uses structural sharing).

The algorithm to achieve immutability is cute. For insertion/deletion it involves recursion down to the required leaf node then recursion back up to replace the parent nodes up to the root. The guts of it is in Leaf.java and NonLeaf.java.
