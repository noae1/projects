

""" A class represnting a node in an AVL tree """

class AVLNode(object):
    """ 
	@type value: str
	@param value: data of your node
	"""
    def __init__(self, value):
        self.value = value
        self.left = None
        self.right = None
        self.parent = None
        self.height = -1
        self.size = 1

    def __repr__(self):
        return "(" + str(self.value) + " size:" + str(self.size) + " : height " + str(self.height) + ")"

    """returns the left child
	@rtype: AVLNode
	@returns: the left child of self, None if there is no left child
	"""

    def getLeft(self):
        return self.left

    """returns the right child

	@rtype: AVLNode
	@returns: the right child of self, None if there is no right child
	"""

    def getRight(self):
        return self.right

    """returns the parent 

	@rtype: AVLNode
	@returns: the parent of self, None if there is no parent
	"""

    def getParent(self):
        return self.parent

    """return the value

	@rtype: str
	@returns: the value of self, None if the node is virtual
	"""

    def getValue(self):
        return self.value

    """returns the height

	@rtype: int
	@returns: the height of self, -1 if the node is virtual
	"""

    def getHeight(self):
        return self.height

    """sets left child

	@type node: AVLNode
	@param node: a node
	"""

    def setLeft(self, node):
        self.left = node

    """returns the size of node

    @type: int 
    @returns: the size of self
    """

    def getSize(self):
        return self.size

    """sets left child

    @type node: AVLNode
    @param node: a node
    """

    def setLeft(self, node):
        self.left = node


    """sets right child
    
    @type node: AVLNode
    @param node: a node
    """


    def setRight(self, node):
        self.right = node


    """sets parent
    
    @type node: AVLNode
    @param node: a node
    """


    def setParent(self, node):
        self.parent = node


    """sets value
    
    @type value: str
    @param value: data
    """
    def setValue(self, value):
        self.value = value

    """sets height

    @type h: int
    @param h: height of node
    """
    def setHeight(self, h):
        self.height = h

    """sets size

    @type s: int
    @param s: size of sub-tree
    """
    def setSize(self, s):
        self.size = s


    """returns whether self is not a virtual node 
    
    @rtype: bool
    @returns: False if self is a virtual node, True otherwise.
    """
    def isRealNode(self):
        return self.height != -1


"""
A class implementing the ADT list, using an AVL tree.
"""


class AVLTreeList(object):

    def __init__(self):
        virtual = AVLNode(None)
        virtual.setSize(0)

        self.virtualNode = virtual
        self.root = virtual
        self.len = 0
        self.firstItem = virtual
        self.lastItem = virtual



    def __repr__(self):  # no need to understand the implementation of this one
        out = ""

        for row in printree(self.root):  # need printree.py file
            out = out + row + "\n"

        return out


# ----------------------
    """returns the root of the tree representing the list

    @rtype: AVLNode
    @returns: the root, None if the list is empty
    """
    def getRoot(self):
        if self.empty():
            return None
        return self.root


    """ updates the height feild of node
    @param node:  node in AVL Tree
    """
    def updateHeightNode(self, node):

        left_height = node.left.height
        right_height = node.right.height
        node.setHeight(1 + max(left_height, right_height))


    """ returns the balance factor of node, 
    calculated according to the heights of left and right child 
    
    @param node:  node in AVL Tree
    @rtype: int
    @returns: node's balance factor 
    """
    def getBalanceFactor(self, node):
        if not node.isRealNode():
            return 0
        return node.left.height - node.right.height


    """returns whether the list is empty
    
    @rtype: bool
    @returns: True if the list is empty, False otherwise
    """
    def empty(self):
        return self.len == 0


    """retrieves the value of the i'th item in the list
    
    @type i: int
    @pre: 0 <= i < self.length()
    @param i: index in the list
    @rtype: str
    @returns: the value of the i'th item in the list
    
    Time complexity : O(log n)
    """
    def retrieve(self, i):
        if i > self.length() or i < 0 or self.empty():
            return None
        return self.Tree_select(self.root, i + 1).value



    """ returns pointer to the node in the i'th place in the sub-tree of given node
    @param node: the root of the sub tree
    @param i:  1 <= i <= self.length
    @rtype: AVLNode
    @return: returns pointer to the node in the i'th place in the sub-tree of given node
    
    Time complexity : O(log n)
    """
    def Tree_select(self, node, i):

        if node.left.isRealNode():
            r = node.left.size + 1
        else:
            r = 1

        if node.size == 1 and i == 1:
            return node

        else:
            if i == r:
                return node
            elif r > i:
                return self.Tree_select(node.left, i)
            else:
                return self.Tree_select(node.right, i - r)



    """ returns the current size of given node, 
    calculated according to the sizes of left and right child 

    @param node:  node in AVL Tree
    @returns: node's current size 
    """
    def calculateSize(self, node):
        if node.isRealNode():
            return node.left.getSize() + node.right.getSize() + 1


    """
    given a node, the function performing left rotation, 
    By changing the relevant fields  
    Time complexity : O(1)
    """
    def leftRotate(self, node):
        a = node.right
        node.setRight(a.left)
        if a.isRealNode() == False:
            a.setParent(node)
        a.setLeft(node)
        a.setParent(node.parent)
        if a.parent.isRealNode():
            if a.parent.left == node:
                a.parent.setLeft(a)
            else:
                a.parent.setRight(a)
            node.right.setParent(node)
        else:  # update the root
            self.root = a
            node.right.setParent(node)
        node.setParent(a)

        # updates size and height of nodes that take part in the rotation
        a.setSize(node.getSize())
        node.setSize(self.calculateSize(node))
        self.updateHeightNode(node)
        self.updateHeightNode(a)

    """
    given a node, the function performing right rotation, 
    By changing the relevant fields 
    Time complexity : O(1)
    """
    def rightRotate(self, node):
        a = node.left
        node.setLeft(a.right)
        if a.isRealNode() == False:
            a.setParent(node)
        a.setRight(node)
        a.setParent(node.parent)
        if a.parent.isRealNode():
            if a.parent.left == node:
                a.parent.setLeft(a)
            else:
                a.parent.setRight(a)

            node.left.setParent(node)
        else:  # update the root
            self.root = a
            node.left.setParent(node)
        node.setParent(a)

        # updates size and height of nodes that take part in the rotation
        a.setSize(node.getSize())
        node.setSize(self.calculateSize(node))
        self.updateHeightNode(node)
        self.updateHeightNode(a)


    """
    given a node, the function performing left rotation and then right rotation
    Time complexity : O(1)
    """
    def left_rightRotate(self, node):
        self.leftRotate(node.left)
        self.rightRotate(node)

    """
    given a node, the function performing right rotation and then left rotation
    Time complexity : O(1)
    """
    def right_leftRotate(self, node):
        self.rightRotate(node.right)
        self.leftRotate(node)


    """inserts val at position i in the list
    
    @type i: int
    @pre: 0 <= i <= self.length()
    @param i: The intended index in the list to which we insert val
    @type val: str
    @param val: the value we inserts
    @rtype: int
    @returns: the number of rebalancing operation due to AVL rebalancing
    
    Time complexity : O(log n) - The cost of finding the i'th item (by Tree_select or max functions)
    and rebalancing the tree from the new node is O(log n) each one.  
    """
    def insert(self, i, val):
        balanceCntInsert = 0
        node = AVLNode(val)

        if i == 0:  # if we insert as first node: update the firstItem field
            self.firstItem = node

        if self.root.isRealNode() == False:  # if we want to insert to EMPTY Root, also update lastItem field
            self.root = node
            node.setParent(self.virtualNode)
            self.lastItem = node

        elif i == self.len:  # insert last
            last = self.max(self.root)

            last.setRight(node)
            node.setParent(last)
            self.lastItem = node

        else:  # get the node at i'th place
            item_i = self.Tree_select(self.root, i + 1)
            if item_i.left.isRealNode() == False:  # if doesnt have left child
                item_i.setLeft(node)
                node.setParent(item_i)
            else:  # if has left child
                predecessor = self.max(item_i.left)
                predecessor.setRight(node)
                node.setParent(predecessor)

        node.setLeft(self.virtualNode)
        node.setRight(self.virtualNode)

        # ---------
        self.len += 1
        # ---------

        self.updateHeightNode(node)  # update height of new node to 0

        balanceCntInsert = self.balance(node)

        return balanceCntInsert


    """ The functuin rebalancing the AVL tree from the given node until the root

    @type node: AVLNode
    @rtype: int
    @returns: the number of rebalancing operation due to AVL rebalancing
    
    Time complexity : O(log n) - Pass by a loop through the route from the given node to the root  
    """
    def balance(self, node):

        balanceCnt = 0
        y = node.parent
        y_child = node

        while y.isRealNode():

            y.setSize(self.calculateSize(y))  # update size and height
            heightBefore = y.getHeight()
            self.updateHeightNode(y)

            if (abs(self.getBalanceFactor(y)) == 2):
                balanceCnt += self.rotate(y, y_child)
            else:
                if heightBefore != y.getHeight():
                    balanceCnt += 1

            y_child = y
            y = y.parent

        return balanceCnt



    """ Performs a suitable rotation according to balance factor 

    @type node, child: AVLNode
    @rtype: int
    @returns: the number of rebalancing operation, depending on the type of rotation
    
    Time complexity : O(1)
    """
    def rotate(self, node, child):
        balanceCnt = 0

        bf = self.getBalanceFactor(node)
        bf_child = self.getBalanceFactor(child)

        # 4 cases:
        # special case in delete:
        if abs(bf) == 2 and bf_child == 0:
            # check Balance Factor of the second child of the root
            if node.left == child:  # coming up from left side
                second_child = node.right

            if node.right == child:  # coming up from right side
                second_child = node.left

            if second_child.isRealNode():  # only if second child of the root is real node
                bf_second_child = self.getBalanceFactor(second_child)
                bf_child = bf_second_child

        if bf == 2:
            if bf_child == 1 or bf_child == 0:
                # ******** rightRotate ********
                self.rightRotate(node)
                balanceCnt = 1

            else:  # bf_child = -1
                #******** left_rightRotate ********
                self.left_rightRotate(node)
                balanceCnt = 2


        elif bf == -2:
            if bf_child == 1:
                #******** right_leftRotate ********
                self.right_leftRotate(node)
                balanceCnt = 2

            else:  # bf_child = -1
                #******** leftRotate ********
                self.leftRotate(node)
                balanceCnt = 1

        return balanceCnt



    """deletes the i'th item in the list
    
    @type i: int
    @pre: 0 <= i < self.length()
    @param i: The intended index in the list to be deleted
    @rtype: int
    @returns: the number of rebalancing operation due to AVL rebalancing
    
    Time complexity : O(log n) - in the worst case, The cost of finding successor, deleting it
    and rebalancing the tree from where there was a structural change, is O(log n) each one.  
    """
    def delete(self, i):
        if i > self.len or self.len == 0 or i <0:
            return -1

        cntBalance = 0
        twoChildrenCase = False

        node = self.Tree_select(self.root, i + 1)
        HaveLeftChild = node.left.isRealNode()
        HaveRightChild = node.right.isRealNode()


        # update firstItem and LastItem pointers
        if i == 0:
            if self.len == 1:
                self.firstItem = self.virtualNode
                self.lastItem = self.virtualNode
                self.root = self.virtualNode
                self.len = 0
                return 0
            else:
                self.firstItem = self.Tree_select(self.root, 2)

        elif i == self.length() - 1:
            self.lastItem = self.Tree_select(self.root, i)

        ## Case 1 - is A leaf
        if not HaveRightChild and not HaveLeftChild:
            replaceNode = self.virtualNode
            updateFrom = node.parent
        ## Case 2 - Left - Has Only Left Child
        elif not HaveRightChild and HaveLeftChild:
            replaceNode = node.left
            updateFrom = node.left
        ## Case 2 - Right - Has Only Right Child
        elif HaveRightChild and not HaveLeftChild:
            replaceNode = node.right
            updateFrom = node.right
        ## Case 3 - Has two children
        else:
            replaceNode = self.successor(node)  # finding the next item in the list and deleting it
            self.len += 1     # The next deletion will again reduce the list length by 1
            twoChildrenCase = True
            cntBalance = self.delete(i + 1)
            updateFrom = replaceNode

        self.switchNodes(node, replaceNode)
        Heightbefore = updateFrom.getHeight()
        self.updateHeightNode(updateFrom)

        # counting changes in height
        if updateFrom.isRealNode() and not twoChildrenCase and Heightbefore != updateFrom.getHeight():
            cntBalance += 1

        updateFrom.setSize(self.calculateSize(updateFrom))
        self.len -= 1

        return cntBalance + self.balance(replaceNode)


    """ Given Old node the function will change pointers so newNode will replace oldNode
        
    @type oldNode, newNode : AVLNode  
    Time complexity : O(1)
    """
    def switchNodes(self, oldNode, newNode):

        parent = oldNode.parent
        if not parent.isRealNode():  # checkes if root
            self.root = newNode
        newNode.setParent(parent)  # changing parent

        if parent.left == oldNode:
            parent.setLeft(newNode)
        if parent.right == oldNode:
            parent.setRight(newNode)

        if oldNode.left != newNode:  # changing left child
            leftNode = oldNode.left
            newNode.setLeft(leftNode)
            leftNode.setParent(newNode)
        if oldNode.right != newNode:  # changing right child
            rightNode = oldNode.right
            newNode.setRight(rightNode)
            rightNode.setParent(newNode)



    """returns the value of the first item in the list
    
    @rtype: str
    @returns: the value of the first item, None if the list is empty
    Time complexity : O(1)
    """
    def first(self):
        if self.empty():
            return None
        return self.firstItem.getValue()


    """returns the value of the last item in the list
    
    @rtype: str
    @returns: the value of the last item, None if the list is empty
    Time complexity : O(1)
    """
    def last(self):
        if self.empty():
            return None
        return self.lastItem.getValue()


    """returns pointer to the first item in the sub-tree of given node
    
    @type node: AVLNode
    @param node : the root of the sub-tree in which the search for the minimum is performed
    @rtype: AVLNode
    @returns:  pointer to the first item in the sub-tree of given node, virtual node if the list is empty
    
    Time complexity : O(log n) - passing through a route from the root to a leaf
    """
    def min(self, node):
        while node.left.isRealNode():
            node = node.left
        return node


    """returns pointer to the last item in the sub-tree of given node
    
    @type node: AVLNode
    @param node : the root of the sub-tree in which the search for the maximum is performed
    @rtype: AVLNode
    @returns:  pointer to the last item in the sub-tree of given node, virtual node if the list is empty
    
    Time complexity : O(log n) - passing through a route from the root to a leaf
    """
    def max(self, node):
        while node.right.isRealNode():
            node = node.right
        return node


    """ returns an array representing list 
    
    @rtype: list
    @returns: a list of strings representing the data structure
    
    Time complexity : O(n) - Creates a new list using the recursive function inorder
    that passes through all the nodes in the tree
    """
    def listToArray(self):
        result = []

        def inorder_rec(node):
            if node.isRealNode():
                inorder_rec(node.left)
                result.append(node.value)
                inorder_rec(node.right)

        inorder_rec(self.root)

        return result


    """ returns the size of the list 
    
    @rtype: int
    @returns: the size of the list
    """
    def length(self):
        return self.len



    """ returns the item that following x in the list
    
    @type x: AVLNode
    @param x : x is node in the AVL Tree
    @rtype: AVLNode
    @returns: The successor of x is the item that following x in the list
    
    Time complexity : O(log n) - in the worst case passing through a route from the root to a leaf
    """
    def successor(self, x):

        if (x.right.isRealNode()):
            return self.min(x.right)

        y = x.parent
        while (y.isRealNode and x == y.right):
            x = y
            y = x.parent
        return y



    """splits the list at the i'th index
    
    @type i: int
    @pre: 0 <= i < self.length()
    @param i: The intended index in the list according to whom we split
    @rtype: list
    @returns: a list [left, val, right], where left is an AVLTreeList representing the list until index i-1,
    right is an AVLTreeList representing the list from index i+1, and val is the value at the i'th index.
    
    Time complexity : O(log n) - As we saw in the lecture, the total time complexity of split using joins is O(log n)
    """

    def split(self, i):
        x = self.Tree_select(self.root, i + 1)
        numOfJoin = 0
        # creating new tree T1 (T1<x)
        T1 = AVLTreeList()
        T1.root = x.left
        T1.len = T1.root.getSize()
        T1.root.setParent(T1.virtualNode)
        # creating new tree T2 (T2>x)
        T2 = AVLTreeList()
        T2.root = x.right
        T2.len = T2.root.getSize()
        T2.root.setParent(T2.virtualNode)
        # deleting x's left and right nodes
        x.setLeft(self.virtualNode)
        x.setRight(self.virtualNode)

        node = x
        parent = x.parent
        nextParent = x.parent
        while nextParent.isRealNode():
            nextParent = parent.parent
            if parent.left == node:  # if x is left child (x<parent)
                parent.setLeft(self.virtualNode)
                T3 = AVLTreeList()
                T3.root = parent.right
                T3.root.setParent(T3.virtualNode)
                T3.len = T3.root.getSize()
                parent.setRight(self.virtualNode)
                numOfJoin += T2.join(AVLNode(parent.getValue()), T3)

            else:  # if x if right child (x>parent)
                parent.setRight(self.virtualNode)
                T4 = AVLTreeList()
                T4.root = parent.left
                T4.root.setParent(T4.virtualNode)
                T4.len = T4.root.getSize()
                parent.setLeft(self.virtualNode)
                numOfJoin += T4.join(AVLNode(parent.getValue()), T1)

                T1.root = T4.root
                T1.len = T4.len
            node = node.parent
            parent = parent.parent

        # update field firstItem for T1 and T2
        if not T1.empty():
            T1.firstItem = T1.min(T1.root)
            T1.lastItem = T1.max(T1.root)
        if not T2.empty():
            T2.firstItem = T2.min(T2.root)
            T2.lastItem = T2.max(T2.root)
        return [T1, x.getValue(), T2]



    """concatenates lst to self
    
    @type lst: AVLTreeList
    @param lst: a list to be concatenated after self
    @rtype: int
    @returns: the absolute value of the difference between the height of the AVL trees joined
    
    Time complexity : O(log n) - deleting the last item in the first list and call join function 
    take O(log n) time each one. 
    """

    def concat(self, T2):
        T1 = self
        T1height = T1.root.getHeight()
        T2height = T2.root.getHeight()
        absDiffHeights = abs(T1height - T2height)  # return this value

        if T2height == -1:  # T2 is empty, do nothing
            return absDiffHeights

        # dealing with T1 Empty or T1 has one value
        if T1height == -1 or T1height == 0:
            if T1height == 0:
                T2.insert(0, T1.root.getValue())
            T1.root = T2.root
            T1.len = T2.len
            T1.lastItem = T2.lastItem
            T1.firstItem = T1.Tree_select(T1.root, 1)
            return abs(T2height - T1height)

        x = T1.lastItem
        T1.delete(T1.len - 1)
        T1.join(x, T2)
        T1.lastItem = T2.lastItem

        return absDiffHeights


    """ Connects L2 to self with Linking node (x)
        
    @precondition : x is reference
    @type L2: AVLTreeList
    @param L2: a list to be concatenated after self
    @rtype: AVLTreeList
    @returns: AVL tree that representing the concatenation list [self, x, L2]
    
    Time complexity : O(log n) - insert first or insert last takes O(log n) time. 
    finding the first node in the heigher tree that it's height is smaller or equal to the height of the second tree
    then rebalancing the tree from current node takes O(height(T2) - height(T1) + 1) time each one, and O(log n) in the worst case.
    """

    def join(self, x, L2):
        L1 = self
        L1node = L1.root
        L2node = L2.root
        L1height = L1node.getHeight()
        L2height = L2node.getHeight()
        L1len = L1.len
        L2len = L2.len

        # dealing with L2 empty - Important for split func
        if L2len == 0:
            L1.insert(L1len, x.getValue())
            return abs(L2height - L1height)

        if L1len == 0:
            L2.insert(0, x.getValue())
            L1.root = L2.root
            L1.len = L2.len
            return abs(L2height - L1height)

        if L2len == 1:
            L1.insert(L1len, x.getValue())
            L1.insert(L1len + 1, L2node.getValue())
            return abs(L2height - L1height)

        if L1len == 1:
            L2.insert(0, x.getValue())
            L2.insert(0, L1node.getValue())
            L1.root = L2.root
            L1.len = L2.len
            return abs(L2height - L1height)

        if L2len == 1:
            L1.insert(L1len, x.getValue())
            L1.insert(L1len + 1, L1node.getValue())
            return abs(L2height - L1height)

        # finding the first node in bigger that it's height is smaller then smaller height
        if L1height < L2height:
            while (L2node.isRealNode() and L2node.getHeight() > L1height):
                L2node = L2node.left
            bigger = L2
            parent = L2node.parent
            x.setParent(parent)
            parent.setLeft(x)

        if L1height > L2height:
            while (L1node.isRealNode() and L1node.getHeight() > L2height):
                L1node = L1node.right
            bigger = L1
            parent = L1node.parent
            x.setParent(parent)
            parent.setRight(x)

        if L1height == L2height:
            bigger = L1
            x.setParent(L1.virtualNode)
            L1.root = x

        #       changeing pointers        #
        x.setLeft(L1node)
        x.setRight(L2node)
        L1node.setParent(x)
        L2node.setParent(x)

        #     Balancing   #
        self.updateHeightNode(x)
        x.setSize(self.calculateSize(x))
        self.balance(x)  # Balance the tree from the x node up to the root

        if bigger == L2:
            # L2 root is the new root for L1 except one case of rotation
            # dealing with bf=0 and bf=2 of the root
            saveL1Root = self.root
            self.root = L2.root
            if saveL1Root.size > self.root.size:
                self.root = saveL1Root

        self.len = L1len + L2len + 1
        return abs(L2height - L1height)




    """searches for a *value* in the list
    
    @type val: str
    @param val: a value to be searched
    @rtype: int
    @returns: the first index that contains val, -1 if not found.
    
    Time complexity : O(n) - As we saw in the lecture series of n calls to successor function, starting from the first item,
    corresponds to in order pass, therefore takes O(n) time.
    """
    def search(self, val):
        cnt = 0
        x = self.firstItem
        while (cnt < self.len and x.getValue() != val):
            succ = self.successor(x)
            x = succ
            cnt += 1
        if cnt < self.len:
            return cnt
        return -1



    """ insert val to the end of the list 
    
    @type val: str
    @param val: a value to be inserted
    @rtype: int
    @returns: the number of rebalancing operation due to AVL rebalancing
    Time complexity : O(log n)
    """
    def append(self, val):
        return self.insert(self.length(), val)

    """ returns the height of the tree , -1 if the tree is empty
    Time complexity : O(1)
    """
    def getTreeHeight(self):
        if not self.root.isRealNode():
            return -1
        else:
            return self.root.getHeight()

##############
# print tree #
##############


def printree(t, bykey=False):
    """Print a textual representation of t
    bykey=True: show keys instead of values"""
    # for row in trepr(t, bykey):
    #        print(row)
    return trepr(t, bykey)


def trepr(t, bykey=False):
    """Return a list of textual representations of the levels in t
    bykey=True: show keys instead of values"""
    if t.isRealNode() == False:
        return ["#"]

    thistr = str(t.key) if bykey else str(t.value)

    return conc(trepr(t.left, bykey), thistr, trepr(t.right, bykey))


def conc(left, root, right):
    """Return a concatenation of textual represantations of
    a root node, its left node, and its right node
    root is a string, and left and right are lists of strings"""

    lwid = len(left[-1])
    rwid = len(right[-1])
    rootwid = len(root)

    result = [(lwid + 1) * " " + root + (rwid + 1) * " "]

    ls = leftspace(left[0])
    rs = rightspace(right[0])
    result.append(ls * " " + (lwid - ls) * "_" + "/" + rootwid * " " + "\\" + rs * "_" + (rwid - rs) * " ")

    for i in range(max(len(left), len(right))):
        row = ""
        if i < len(left):
            row += left[i]
        else:
            row += lwid * " "

        row += (rootwid + 2) * " "

        if i < len(right):
            row += right[i]
        else:
            row += rwid * " "

        result.append(row)

    return result


def leftspace(row):
    """helper for conc"""
    # row is the first row of a left node
    # returns the index of where the second whitespace starts
    i = len(row) - 1
    while row[i] == " ":
        i -= 1
    return i + 1


def rightspace(row):
    """helper for conc"""
    # row is the first row of a right node
    # returns the index of where the first whitespace ends
    i = 0
    while row[i] == " ":
        i += 1
    return i

