# Написать функцию, которая проверяет, является ли дерево симметричным
#

# true
#     1
#   2    2
#  4  5 5  4
#
#  false
#     1
#   3    2
#  4    5

# b1.value
# b1.left
# b1.right

def is_tree_sym(t):
	return is_sym(t.left, t.right)

def is_sym(b1, b2):
	if b1 is None and b2 is not None:
		return False
	if b2 is None and b1 is not None:
		return False
	if b1 is None and b2 is None:
		return True

	if b1.value != b2.value:
		return False

	if not is_sym(b1.left, b2.right):
		return False
	if not is_sym(b1.right, b2.left):
		return False

	return True