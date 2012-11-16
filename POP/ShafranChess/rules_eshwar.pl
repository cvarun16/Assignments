initialize(Game,Position,Player):- initial_position(Position),/*isReach([6,4,8],[6,5,9],[p,w],Position)*/move([[1,1,10]|[2,1,9]],Position,Player).

%display(A) :- print(A),print('\n'),print('B').

/* a =1,b=2,c=3 ....i=9*/
/*[letter,left,right]*/
initial_position(Position):- Position = [[1,1,10],[r,w],[2,1,9],[h,w],[3,1,8],[b,w],[4,1,7],[q,w],[5,1,6],[k,w],[6,2,6],[b,w],[7,3,6],[h,w],[8,4,6],[b,w],[9,5,6],[r,w],
                                         [1,2,1],[p,w],[2,2,10],[p,w],[3,2,9],[p,w],[4,2,8],[p,w],[5,2,7],[p,w],[6,3,7],[p,w],[7,4,7],[p,w],[8,5,7],[p,w],[9,6,7],[p,w],
                                         [1,3,2],[n,n],[2,3,1],[n,n],[3,3,10],[n,n],[4,3,9],[n,n],[5,3,8],[n,n],[6,4,8],[n,n],[7,5,8],[n,n],[8,6,8],[n,n],[9,7,8],[n,n],
                                         [1,4,3],[n,n],[2,4,2],[n,n],[3,4,1],[n,n],[4,4,10],[n,n],[5,4,9],[n,n],[6,5,9],[n,n],[7,6,9],[n,n],[8,6,9],[n,n],[8,6,9],[n,n],
                                         [1,5,4],[p,b],[2,5,3],[n,n],[3,5,2],[n,n],[4,5,1],[n,n],[5,5,10],[n,n],[6,6,10],[n,n],[7,7,10],[n,n],[8,8,10],[n,n],[9,9,10],[p,b],
                                         [1,6,5],[r,b],[2,6,4],[p,b],[3,6,3],[n,n],[4,6,2],[n,n],[5,6,1],[n,n],[6,7,1],[n,n],[7,8,1],[n,n],[8,9,1],[p,b],[9,10,1],[r,b],
                                         [2,7,5],[b,b],[3,7,4],[p,b],[4,7,3],[n,n],[5,7,2],[n,n],[6,8,2],[n,n],[7,9,2],[p,b],[8,10,2],[r,b],
                                         [3,8,5],[h,b],[4,8,4],[p,b],[5,8,3],[n,n],[6,9,3],[p,b],[7,10,3],[b,b],
                                         [4,9,5],[b,b],[5,9,4],[p,b],[6,10,4],[q,b],
                                         [5,10,5],[k,b]].

%To get input in normal chess form and convert it into 3-D coordinates
initialize_board_coordinates(Coordinates):-Coordinates=[[a,1],[1,1,10],[a,2],[1,2,1],[a,3],[1,3,2],[a,4],[1,4,3],[a,5],[1,5,4],[a,6],[1,6,5],
                                                [b,1],[2,1,9],[b,2],[2,2,10],[b,3],[2,3,1],[b,4],[2,4,2],[b,5],[2,5,3],[b,6],[2,6,4],[b,7],[2,7,5],
                                  [c,1],[3,1,8],[c,2],[3,2,9],[c,3],[3,3,10],[c,4],[3,4,1],[c,5],[3,5,2],[c,6],[3,6,3],[c,7],[3,7,4],[c,8],[3,8,5],
                    [d,1],[4,1,7],[d,2],[4,2,8],[d,3],[4,3,9],[d,4],[4,4,10],[d,5],[4,5,1],[d,6],[4,6,2],[d,7],[4,7,3],[d,8],[4,8,4],[d,9],[4,9,5],
    [e,1],[5,1,6],[e,2],[5,2,7],[e,3],[5,3,8],[e,4],[5,4,9],[e,5],[5,5,10],[e,6],[5,6,1],[e,7],[5,7,2],[e,8],[5,8,3],[e,9],[5,9,4],[e,10],[5,10,5],
                  [f,2],[6,2,6],[f,3],[6,3,7],[f,4],[6,4,8],[f,5],[6,5,9],[f,6],[6,6,10],[f,7],[6,7,1],[f,8],[6,8,2],[f,9],[6,9,3],[f,10],[6,10,4],
                                [g,3],[7,3,6],[g,4],[7,4,7],[g,5],[7,5,8],[g,6],[7,6,9],[g,7],[7,7,10],[g,8],[7,8,1],[g,9],[7,9,2],[g,10],[7,10,3],
                                          [h,4],[8,4,6],[h,5],[8,5,7],[h,6],[8,6,8],[h,7],[8,7,9],[h,8],[8,8,10],[h,9],[8,9,1],[h,10],[8,10,2],
                                                        [i,5],[9,5,6],[i,6],[9,6,7],[i,7],[9,7,8],[i,8],[9,8,9],[i,9],[9,9,10],[i,10],[9,10,1] ].


get3DFrom2D(TwoPosition,ThreePosition,Coordinates):-Coordinates=[TwoPosition|Rest],Rest=[ThreePosition|Y].
get3DFrom2D(TwoPosition,ThreePosition,[Y|Rest]):-get3DFrom2D(TwoPosition,ThreePosition,Rest).

getinputmove(FromPosition,ToPosition,Move):-initialize_board_coordinates(Coordinates) .




/* Gets the next player given the other player */
next_player(w,Player1):- Player1 = b.
next_player(b,Player1):- Player1 = w.

not_Color(b,C1):-C1 = w.
not_Color(w,C1):-C1 = b.

/* the move function*/
/*takes the Move as input and moves the piece from position to position1*/

move(Move,Position,Position1):- Move = [CurrentPosition|NewPosition],getPiece(CurrentPosition,CurrentPiece,Position),newPosition(NewPosition,CurrentPiece,Position,Positionint),emptyOldPosition(CurrentPosition,CurrentPiece,Positionint,Position1).


getPiece(GivenPosition,Piece,Position):-Position = [GivenPosition|Rest],Rest=[Piece|_].
getPiece(GivenPosition,Piece,[Y|Rest]):-getPiece(GivenPosition,Piece,Rest).


newPosition(NewPosition,Piece,Position,Positionint):- Position=[NewPosition|Rest],Rest=[CurRest|Y],NewRest=[Piece|Y],Positionint=[NewPosition|NewRest].
newPosition(NewPosition,Piece,[Z|Rest],[Z|Positionint]):- newPosition(NewPosition,Piece,Rest,Positionint). 

emptyOldPosition(CurrentPosition,Piece,Position,Positionint):-Position=[CurrentPosition|Rest],Rest = [CurrentPiece|Y],NewRest=[[n,n]|Y], Positionint=[CurrentPosition|NewRest].
emptyOldPosition(CurrentPosition,Piece,[Z|Rest],[Z|Positionint]):- emptyOldPosition(CurrentPosition,Piece,Rest,Positionint).

/* Predicate to check if the cell exists on the board */
checkCellExists(Cell,Board):- Board = [Cell|Rest].
checkCellExists(Cell,[Y|Rest]):-checkCellExists(Cell,Rest).

/*Checks if a particular cell is currently free*/
isFree(Position,Board):-getPiece(Position,Piece,Board),Piece=[n,n].

/*Returns Y as the absolute value of X*/
abs(X,Y):-X>0,!,Y=X.
abs(X,Y):-Z is -1 * X,Y=Z.

mOD(X,Y):-abs(X,Z),Z > 4,Y is X mod 10.
mOD(X,Y):-Y=X.

/*Does x= (y+r)mod 10*/
modIndex(X,R,Y):-T1 is X+R,T1>10,!,Y is T1 mod 10.
modIndex(X,R,Y):-Y is X+R.

/*Returns the distance from the nearest 10*/
mod5(X,Y):-T is X mod 10,T>5,Y is 10-T .
mod5(X,Y):-T is X mod 10,Y is T.

test(Y):-Z is 1-3,Y is Z mod 10.

kingReach(D1,D2,D3):-abs(D1,R1),abs(D2,R2),abs(D3,R3),R1=:=1,R2=:=1,R3=:=2.
kingReach(D1,D2,D3):-abs(D1,R1),abs(D2,R2),abs(D3,R3),R1=:=1,R2=:=2,R3=:=1.
kingReach(D1,D2,D3):-abs(D1,R1),abs(D2,R2),abs(D3,R3),R1=:=2,R2=:=1,R3=:=1.
kingReach(D1,D2,D3):-abs(D1,R1),abs(D2,R2),abs(D3,R3),R1=:=1,R2=:=1,R3=:=0.
kingReach(D1,D2,D3):-abs(D1,R1),abs(D2,R2),abs(D3,R3),R1=:=1,R2=:=0,R3=:=1.
kingReach(D1,D2,D3):-abs(D1,R1),abs(D2,R2),abs(D3,R3),R1=:=0,R2=:=1,R3=:=1.
   

horseReach(D1,D2,D3):- abs(D1,A1),abs(D2,A2),abs(D3,A3),A1=\=0,A2=\=0,A3=\=0,A1=\=A2,A2=\=A3,T1 is A1 + A2,Sum is T1 + A3 , Sum =:= 6.

/*Checks if position to position1 along a diagonal is free to be reached on the current board*/
diagonalReach(Position,Position1,Board):-topRight(Position,Position1,Board).
diagonalReach(Position,Position1,Board):-topLeft(Position,Position1,Board).
diagonalReach(Position,Position1,Board):-right(Position,Position1,Board).
diagonalReach(Position,Position1,Board):-left(Position,Position1,Board).
diagonalReach(Position,Position1,Board):-bottomRight(Position,Position1,Board).
diagonalReach(Position,Position1,Board):-bottomLeft(Position,Position1,Board).

/*Checks for the top right diagonal*/
topRight(Position,Position1,Board):-Position=Position1.
topRight(Position,Position1,Board):-Position=[X,Y,Z],X1 is X+1,Y1 is Y+2,modIndex(Z,1,Z1),checkCellExists([X1,Y1,Z1],Board),isFree([X1,Y1,Z1],Board),
                                    topRight([X1,Y1,Z1],Position1,Board).

/*Checks for the top left diagonal*/
topLeft(Position,Position1,Board):-Position=Position1.
topLeft(Position,Position1,Board):-Position=[X,Y,Z],X1 is X-1,Y1 is Y+1,modIndex(Z,2,Z1),checkCellExists([X1,Y1,Z1],Board),isFree([X1,Y1,Z1],Board),
                                    topLeft([X1,Y1,Z1],Position1,Board).

/*Checks for the left diagonal*/
left(Position,Position1,Board):-Position=Position1.
left(Position,Position1,Board):-Position=[X,Y,Z],X1 is X-2,Y1 is Y-1,modIndex(Z,1,Z1),checkCellExists([X1,Y1,Z1],Board),isFree([X1,Y1,Z1],Board),
                                    left([X1,Y1,Z1],Position1,Board).

/*Checks for the right diagonal*/
right(Position,Position1,Board):-Position=Position1.
right(Position,Position1,Board):-Position=[X,Y,Z],X1 is X+2,Y1 is Y+1,modIndex(Z,-1,Z1),checkCellExists([X1,Y1,Z1],Board),isFree([X1,Y1,Z1],Board),
                                    right([X1,Y1,Z1],Position1,Board).

/*Checks for the bottomleft diagonal*/
bottomLeft(Position,Position1,Board):-Position=Position1.
bottomLeft(Position,Position1,Board):-Position=[X,Y,Z],X1 is X-1,Y1 is Y-2,modIndex(Z,-1,Z1),checkCellExists([X1,Y1,Z1],Board),isFree([X1,Y1,Z1],Board),
                                    bottomLeft([X1,Y1,Z1],Position1,Board).

/*Checks for the bottomright diagonal*/
bottomRight(Position,Position1,Board):-Position=Position1.
bottomRight(Position,Position1,Board):-Position=[X,Y,Z],X1 is X+1,Y1 is Y-1,modIndex(Z,-2,Z1),checkCellExists([X1,Y1,Z1],Board),isFree([X1,Y1,Z1],Board),
                                    bottomLeft([X1,Y1,Z1],Position1,Board).


/*Checks if position to position1 along a straight is free to be reached on the current board*/
straightReach(Position,Position1,Board):-top(Position,Position1,Board).
straightReach(Position,Position1,Board):-down(Position,Position1,Board).
straightReach(Position,Position1,Board):-firstIndexTop(Position,Position1,Board).
straightReach(Position,Position1,Board):-firstIndexDown(Position,Position1,Board).
straightReach(Position,Position1,Board):-secondIndexTop(Position,Position1,Board).
straightReach(Position,Position1,Board):-secondIndexDown(Position,Position1,Board).

/*Checks for the top straight*/
top(Position,Position1,Board):-Position=Position1.
top(Position,Position1,Board):-Position=[X,Y,Z],X1 is X,Y1 is Y+1,modIndex(Z,1,Z1),checkCellExists([X1,Y1,Z1],Board),isFree([X1,Y1,Z1],Board),
                                    top([X1,Y1,Z1],Position1,Board).

/*Checks for the bottom straight*/
down(Position,Position1,Board):-Position=Position1.
down(Position,Position1,Board):-Position=[X,Y,Z],X1 is X,Y1 is Y-1,modIndex(Z,-1,Z1),checkCellExists([X1,Y1,Z1],Board),isFree([X1,Y1,Z1],Board),
                                    down([X1,Y1,Z1],Position1,Board).

/*Checks for the first index top striaght*/
firstIndexTop(Position,Position1,Board):-Position=Position1.
firstIndexTop(Position,Position1,Board):-Position=[X,Y,Z],X1 is X-1,Y1 is Y,modIndex(Z,1,Z1),checkCellExists([X1,Y1,Z1],Board),isFree([X1,Y1,Z1],Board),
                                    firstIndexTop([X1,Y1,Z1],Position1,Board).

/*Checks for the first index bottom straight*/
firstIndexDown(Position,Position1,Board):-Position=Position1.
firstIndexDown(Position,Position1,Board):-Position=[X,Y,Z],X1 is X+1,Y1 is Y,modIndex(Z,-1,Z1),checkCellExists([X1,Y1,Z1],Board),isFree([X1,Y1,Z1],Board),
                                    firstIndexDown([X1,Y1,Z1],Position1,Board).

/*Checks for the second index top straight*/
secondIndexTop(Position,Position1,Board):-Position=Position1.
secondIndexTop(Position,Position1,Board):-Position=[X,Y,Z],X1 is X+1,Y1 is Y+1,Z1 is Z,checkCellExists([X1,Y1,Z1],Board),isFree([X1,Y1,Z1],Board),
                                    secondIndexTop([X1,Y1,Z1],Position1,Board).

/*Checks for the second index down straight*/
secondIndexDown(Position,Position1,Board):-Position=Position1.
secondIndexDown(Position,Position1,Board):-Position=[X,Y,Z],X1 is X-1,Y1 is Y-1,Z1 is Z,checkCellExists([X1,Y1,Z1],Board),isFree([X1,Y1,Z1],Board),
                                    secondIndexDown([X1,Y1,Z1],Position1,Board).


/* Check the Pawn move. The trickiest part. Have to check for base case moves*/

/* Entirely from white s perspective */
pawnReach(Position,Position1,Board,w):-Position=[X,Y,Z],Position1=[X1,Y1,Z1],X1=:=X,Y=:=Y1-1,modIndex(Z1,-1,R),R=:=Z,getPiece(Position1,Piece,Board),
                                       Piece=[_|Color],Color=:=n.
pawnReach(Position,Position1,Board,w):-Position=[X,Y,Z],Position1=[X1,Y1,Z1],X1=:=X-1,Y1=:=Y,modIndex(Z1,-1,R),R=:=Z,
                                       getPiece(Position1,Piece,Board),
                                       Piece=[_|Color],Color=:=n.
pawnReach(Position,Position1,Board,w):-Position=[X,Y,Z],Position1=[X1,Y1,Z1],X1-1=:=X,Y1-1=:=Y,Z1=:=Z,
                                       getPiece(Position1,Piece,Board),
                                       Piece=[_|Color],Color=:=n.
pawnReach(Position,Position1,Board,w):-Position=[X,Y,Z],Position1=[X1,Y1,Z1],X1=:=X-1,Y1-1=:=Y,modIndex(Z1,-2,R),R=:=Z,getPiece(Position1,Piece,Board),
                                       Piece=[_|Color],Color=:=b.
pawnReach(Position,Position1,Board,w):-Position=[X,Y,Z],Position1=[X1,Y1,Z1],X1-1=:=X,Y1-2=:=Y,modIndex(Z1,-1,R),R=:=Z,
                                       getPiece(Position1,Piece,Board),
                                       Piece=[_|Color],Color=:=b.

/* Hard code the base cases for white */
pawnReach([2,2,10],[2,4,2],Board,w):-isFree([2,3,1],Board),isFree([2,4,2],Board).
pawnReach([3,2,9],[3,4,1],Board,w):-isFree([3,3,10],Board),isFree([3,4,1],Board).
pawnReach([8,5,7],[8,7,9],Board,w):-isFree([8,6,8],Board),isFree([8,7,8],Board).
pawnReach([7,4,7],[7,6,9],Board,w):-isFree([7,5,8],Board),isFree([7,6,9],Board).
%pawnReach([2,2,10],[2,4,2],Board,w):-isFree([2,3,1],Board),isFree([2,4,2],Board).
%pawnReach([2,2,10],[2,4,2],Board,w):-isFree([2,3,1],Board),isFree([2,4,2],Board).
%pawnReach([2,2,10],[2,4,2],Board,w):-isFree([2,3,1],Board),isFree([2,4,2],Board).

%%%%%%%%%%%%%%%%%%%%%%%%%%%% Still incomplete here %%%%%%%%%%%%%%%%%%%%%%

/* Negation for reach */
notIsReach(Position,Position1,Piece,Board):-isReach(Position,Position1,Piece,Board),!,fail.
notIsReach(Position,Position1,Piece,Board).



/*Predicate for the King*/
isReach(Position,Position1,Piece,Board):- Piece=[k|Color],
                                                Color=[COL|_],
                                                getPiece(Position1,P,Board),
                                                P=[_|C],
                                                Color =\= C,
                                                Position=[X,Y,Z],
                                                Position1=[X1,Y1,Z1],
                                                D1 is X - X1,
                                                D2 is Y-Y1,
                                                R3 is Z-Z1,
                                                mOD(R3,D3),
                                                kingReach(D1,D2,D3).

/*Predicate for reachability of horse */
isReach(Position,Position1,Piece,Board):- Piece=[h|Color],
                                                Color=[COL|_],
                                                getPiece(Position1,P,Board),
                                                P=[_|C],
                                                Color =\= C,
                                                Position=[X,Y,Z],
                                                Position1=[X1,Y1,Z1],
                                                D1 is X - X1,
                                                D2 is Y-Y1,
                                                R3 is Z-Z1,
                                                mOD(R3,D3),
                                                horseReach(D1,D2,D3).
                                                                        
 /*Predicate for reachability of bishop */
isReach(Position,Position1,Piece,Board):- Piece=[b|Color],
                                                Color=[COL|_],
                                                getPiece(Position1,P,Board),
                                                P=[_|C],
                                                Color =\= C,
                                                Position=[X,Y,Z],
                                                Position1=[X1,Y1,Z1],
                                                diagonalReach(Position,Position1,Board).
                                                                                                     
  /*Predicate for reachability of rook */
isReach(Position,Position1,Piece,Board):- Piece=[r|Color],
                                                Color=[COL|_],
                                                getPiece(Position1,P,Board),
                                                P=[_|C],
                                                Color =\= C,
                                                Position=[X,Y,Z],
                                                Position1=[X1,Y1,Z1],
                                                straightReach(Position,Position1,Board).
                                   
/*Predicate for reachability of queen : just combine rook and bishop :P */
isReach(Position,Position1,Piece,Board):- Piece=[q|Color],
                                                Color=[COL|_],
                                                getPiece(Position1,P,Board),
                                                P=[_|C],
                                                Color =\= C,
                                                Position=[X,Y,Z],
                                                Position1=[X1,Y1,Z1],
                                                straightReach(Position,Position1,Board).
                                   
isReach(Position,Position1,Piece,Board):- Piece=[q|Color],
                                                Color=[COL|_],
                                                getPiece(Position1,P,Board),
                                                P=[_|C],
                                                Color =\= C,
                                                Position=[X,Y,Z],
                                                Position1=[X1,Y1,Z1],
                                                diagonalReach(Position,Position1,Board).

/* Predicate for reachability of a pawn */
isReach(Position,Position1,Piece,Board):- Piece=[p|Color],
                                                Color=[COL|_],
                                                getPiece(Position1,P,Board),
                                                P=[_|C],
                                                Color =\= C,
                                                Position=[X,Y,Z],
                                                Position1=[X1,Y1,Z1],
                                                pawnReach(Position,Position1,Board,Color).

