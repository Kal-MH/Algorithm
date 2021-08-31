int N,S,a[22],i=0;
void B(int x,int y){
	if(x>-1&&y==S)i++;
	while(++x<N)B(x,y+a[x]);}
int main(){
	scanf("%d%d",&N,&S);
	while(i<N)scanf("%d",&a[i++]);
	B(-1,0),printf("%d\n",i-N);}
