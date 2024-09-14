document.addEventListener('DOMContentLoaded',function(){

    const list =document.querySelector('#movie-list ul');
    const addForm =document.querySelector('form')
  
    //delete movie
    list.addEventListener('click',(e) => {
      if(e.target.className == 'delete'){
          const li = e.target.parentElement;
          li.parentNode.removeChild(li);
      }
  
      else if(e.target.className == 'edit'){
        const li= e.target.parentElement;
        li.parentNode.removeChild(li);
        const movieName = li.querySelector('span.name');
        const btn=document.getElementById('add')
        const edit_input = document.getElementById('inn')
  
        btn.textContent='Update'
        edit_input.value = movieName.textContent;
  
        btn.addEventListener('click',()=>{
          if(edit_input.value.trim() !==""){
            btn.textContent = 'Add';
          }
        })
      }
   });
  
  
   addForm.addEventListener('submit',function(e){
    e.preventDefault();
  
    //creating elements
   const valuee = addForm.querySelector('input[type = "text"]').value;
  
   const li = document.createElement('li');
   const movieName= document.createElement('span');
   const deleteBtn =  document.createElement('span');
   const editBtn = document.createElement('span')
  
  
  //add text content
   movieName.textContent = valuee;
   deleteBtn.textContent = 'Delete';
   editBtn.textContent = 'Edit';
  
  
  //add classes
  movieName.classList.add('name');
  deleteBtn.classList.add('delete');
  editBtn.classList.add('edit')
  
  
  //append to DOM
  li.appendChild(movieName);
  li.appendChild(deleteBtn);
  li.appendChild(editBtn);
  list.appendChild(li);
  
  addForm.querySelector('input[type = "text"]').value='';
  
  }
  );
  })