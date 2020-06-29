function getFinalChecklist(diff, mappings) {
  let checklist = getChecklist(diff, mappings);
  let formattedChecklist = getFormattedChecklist(checklist);
  return formattedChecklist;
}


function getChecklist(diff, mappings) {
  let checklist = []

  mappings.forEach(mapping => {
    const keywords = mapping.keywords
    for (let i = 0; i < keywords.length; i++) {
      if (diff.toLowerCase().includes(keywords[i].toLowerCase())) {
        checklist.push(mapping.comment)
        break;
      }
    }
  })
  return checklist;
}


function getFormattedChecklist(checklist) {
  let formattedChecklist = '';
  if (checklist.length > 0) {
    formattedChecklist = '**Checklist:**'

    for (let i = 0; i < checklist.length; i++) {
      formattedChecklist += '\n';
      formattedChecklist += '- [ ] ' + checklist[i];
    }
  }
  return formattedChecklist;
}

const data = require('./mapping.json');
const mappings = data.mappings


const fs = require('fs')
var path = require('path');
var filePath = path.join(__dirname, '.', 'diff.txt');

fs.readFile(filePath, 'utf8', function (err,data) {
  if (err) {
    return console.log(err);
  }
  let checklist = getFinalChecklist(data, mappings);
  console.log(checklist)

  fs.writeFile('checklist.md', checklist, function (err) {
    if (err) {
      return console.log(err);
    }
  });
});

