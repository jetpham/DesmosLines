(function () {
  "use strict";
  let index = 0;
  let expressions = Calc.getExpressions();
  if (
    typeof expressions[index] !== "undefined" &&
    expressions[index].type === "table"
  ) {
    let expr = expressions[index].columns;
    let colNames = expr.map((col) => {
      return col.latex;
    });
    let helpProm = new Promise((resolve, reject) => {
      let lastIdx = colNames.length - 1;
      let output = {};
      let helpEx = [];
      colNames.forEach(function (colHeader, i) {
        // console.log(item);
        helpEx[i] = Calc.HelperExpression({ latex: colHeader });
        helpEx[i].observe("listValue", () => {
          output[colHeader] = helpEx[i].listValue;
          if (i === lastIdx) {
            resolve(output);
          }
          helpEx[i].unobserve("listValue");
        });
      });
    });
    helpProm.then((output) => {
      let colArray = [];
      // array of length cannot be negative
      let maxSize = 0;
      colNames.forEach((colHeader) => {
        if (output[colHeader].length > maxSize) {
          maxSize = output[colHeader].length;
        }
        colArray.push([colHeader, ...output[colHeader]]);
      });
      maxSize++;
      let transposed = [];
      for (var idxData = 0; idxData < maxSize; idxData++) {
        transposed.push(
          colNames.map((itemData, idxHead) => {
            if (
              typeof colArray[idxHead] !== "undefined" &&
              typeof colArray[idxHead][idxData] !== "undefined"
            ) {
              return colArray[idxHead][idxData];
            } else {
              return "";
            }
          })
        );
      }
      let copyText = transposed
        .map((item, i) => {
          return item.join("\t");
        })
        .join("\n");
      alert(copyText);
      console.log(copyText);
    });
  } else {
    console.warn(
      `Expression number ${index} does not exist or is not a table.`
    );
  }
})();
