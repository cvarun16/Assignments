; Function returns a list with last element removed
(defun removeLastElement (lst) (reverse (cdr (reverse lst))))
    
;Inserts element in lst(based on length of the elements). Assuming lst is in a sorted fashion
(defun orderedInsert (element lst)
    (cond
        ((null lst) element)
        ((>= (length (first element)) (length (car (last lst)))) (append lst element))
       (t (append (orderedInsert element (removeLastElement lst)) (last lst)))
    )
)

; The function which does the sorting
(defun insertionSort (lst)
    (cond
     ( (> (length lst) 1) (orderedInsert (last lst) (insertionSort (removeLastElement lst)) ))
      (t lst)
    )
)
