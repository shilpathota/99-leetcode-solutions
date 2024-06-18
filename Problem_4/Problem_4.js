/**
 * @param {string[]} strs
 * @return {string}
 */
var longestCommonPrefix = function(strs) {
                var res = "";
        strs.sort();
        let first = strs[0];
        let last = strs[strs.length - 1];
        for(let i=0;i<Math.min(first.length,last.length);i++){
            if(first[i]!==last[i]){
                return res;
            }
            res=res.concat(first[i]);
        }
        return res;
};
