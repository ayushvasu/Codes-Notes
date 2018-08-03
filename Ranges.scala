//split to equal ranges

    val rangesTuple = List((1l, 100l),(110l, 150l),(200l, 230l))
    def getRowCountInRange (range: (Long,Long)) = (range._2 - range._1) + 1
    val rangeSumList = rangesTuple.map{ x => getRowCountInRange(x._1,x._2)}

    def getRanges(rows:Long,split: Long) = {
      var ranges:List[Long] = rangeSumList
      var currRows = rangesTuple
      for(i <- 0l until split) yield {
        var currRowsSum = 0l
        var currRowsList = new scala.collection.mutable.ListBuffer[(Long, Long)]
        while (currRowsSum < rows && !currRows.isEmpty) {
          if (ranges.head <= rows - currRowsSum) {
            currRowsSum += ranges.head
            ranges = ranges.tail
            currRowsList = currRowsList :+ (currRows.head._1, currRows.head._2)
            currRows = currRows.tail
          } else if (ranges.head > rows - currRowsSum) {
            currRowsSum += rows
            ranges = (ranges.head - rows) :: ranges.tail
            currRowsList = currRowsList :+ (currRows.head._1, currRows.head._1 + rows - 1)
            currRows = (currRows.head._1 + rows, currRows.head._2) :: currRows.tail
          }
        }
        currRowsList.toList
      }
    }

    getRanges(86,2)
