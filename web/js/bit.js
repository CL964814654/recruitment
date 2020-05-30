var Bit = {
    /**
     * 测试指定位是否为1
     * @param value 待测试的值
     * @param idx 指定的位
     * @return {boolean} 指定位是否为1
     */
    test:function (value,idx) {
        return (value&(1<<idx))!=0;
    },

    /**
     * 测试指定范围位数的值
     * @param value 待测试的值
     * @param idx1 高位
     * @param idx2 低位
     * @return {number} 指定范围位数的值
     */
    testRange:function (value,idx1,idx2) {
        return (value&((1<<(idx1+1))-(1<<idx2)))>>idx2;
    },

    /**
     * 将某位置1
     * @param value 待置位的数
     * @param idx 指定位
     * @return {number} 置位之后的数
     */
    set:function (value, idx) {
        return value|(1<<idx);
    },

    /**
     *
     * @param value
     * @param idx1
     * @param idx2
     * @param v
     * @return {number}
     */
    setRange:function (value,idx1,idx2,v) {
        v = (v&((1<<(idx1-idx2+1))-1));//保证设置的值在合理范围内
        return value&(~((1<<(idx1+1))-(1<<idx2)))|(v<<idx2);//指定位清0再置位
    },

    /**
     * 将某位置0
     * @param value 待置位的数
     * @param idx 指定位
     * @return {number} 置位之后的数
     */
    reset:function (value,idx) {
        return value&(~(1<<idx));
    }
}