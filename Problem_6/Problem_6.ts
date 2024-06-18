function plusOne(digits: number[]): number[] {
        let i= digits.length-1;
    while(digits[i]==9){
        if(i==0){
            let newarr = Array.from(new Array(digits.length+1), (x,i) => 0);
            newarr[0]=1;
            return newarr;
        }
        digits[i]=0;
        i-=1;
    }
    digits[i]+=1;
    return digits;
};
