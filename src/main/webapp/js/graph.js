// create an array with nodes
var nodes = new vis.DataSet([
    {id: 'A', label: 'Node A'},
    {id: 'B', label: 'Node B'},
    {id: 'C', label: 'Node C'},
    {id: 'D', label: 'Node D'},
    {id: 'E', label: 'Node E'}
]);

// create an array with edges
var edges = new vis.DataSet([
    {from: 'A', to: 'C'},
    {from: 'A', to: 'B'},
    {from: 'B', to: 'D'},
    {from: 'B', to: 'E'},
    {from: 'C', to: 'C'}
]);

// create a network
var container = document.getElementById('graph');
var data = {
    nodes: nodes,
    edges: edges
};
var options = {};
var network = new vis.Network(container, data, options);